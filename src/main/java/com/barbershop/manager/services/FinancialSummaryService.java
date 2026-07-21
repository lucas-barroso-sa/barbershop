package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.financial.*;
import com.barbershop.manager.models.entities.BankAccount;
import com.barbershop.manager.models.entities.FinancialMovement;
import com.barbershop.manager.models.enums.MovementStatus;
import com.barbershop.manager.models.enums.MovementType;
import com.barbershop.manager.repositories.BankAccountRepository;
import com.barbershop.manager.repositories.FinancialMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FinancialSummaryService {

    @Autowired
    private FinancialMovementService financialMovementService;

    @Autowired
    private BankAccountService bankAccountService;



    @Transactional(readOnly = true)
    public FinancialSummaryDTO getSummary() {
        LocalDate today = LocalDate.now();
        LocalDate endOfWeek = today.plusDays(7);

        // 1. Busca TODAS as movimentações do banco uma única vez (para usar nos KPIs e no Gráfico)
        List<FinancialMovement> allMovements = financialMovementService.findAllEntities();

        // 2. Filtra apenas as PENDENTES para os cards superiores (Contas a Pagar / Receber)
        List<FinancialMovement> pendingMovements = allMovements.stream()
                .filter(m -> m.getMovementStatus() == MovementStatus.PENDING)
                .collect(Collectors.toList());

        List<FinancialMovement> pendingIncomes = pendingMovements.stream()
                .filter(m -> m.getMovementType() == MovementType.INCOME)
                .collect(Collectors.toList());

        List<FinancialMovement> pendingExpenses = pendingMovements.stream()
                .filter(m -> m.getMovementType() == MovementType.EXPENSE)
                .collect(Collectors.toList());

        MovementSummaryDTO receivables = calculateSummary(pendingIncomes, today, endOfWeek);
        MovementSummaryDTO payables = calculateSummary(pendingExpenses, today, endOfWeek);

        // 3. Busca Contas Bancárias usando o Service para as Disponibilidades
        List<BankAccount> bankAccounts = bankAccountService.findAllEntities();

        List<AccountBalanceDTO> accountDtos = bankAccounts.stream()
                .map(acc -> new AccountBalanceDTO(acc.getName(), acc.getBalance()))
                .collect(Collectors.toList());

        BigDecimal totalBalance = accountDtos.stream()
                .map(AccountBalanceDTO::balance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        AvailabilitySummaryDTO availabilities = new AvailabilitySummaryDTO(totalBalance, accountDtos);

        // 4. Gera os dados do Gráfico (o método generateChartData vai filtrar apenas as SETTLED lá dentro)
        List<ChartDataDTO> chartData = generateChartData(allMovements);

        // 5. Formatação do Mês Atual (Ex: Julho/2026)
        String currentMonthYear = today.format(DateTimeFormatter.ofPattern("MMMM/yyyy", new Locale("pt", "BR")));
        currentMonthYear = currentMonthYear.substring(0, 1).toUpperCase() + currentMonthYear.substring(1);

        // 6. Montagem e retorno do DTO final
        return new FinancialSummaryDTO(
                currentMonthYear,
                payables,
                receivables,
                availabilities,
                chartData
        );
    }

    /**
     * Método auxiliar para calcular Atrasos, Hoje e Semana
     */
    private MovementSummaryDTO calculateSummary(List<FinancialMovement> movements, LocalDate today, LocalDate endOfWeek) {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal overdue = BigDecimal.ZERO;
        BigDecimal todayAmount = BigDecimal.ZERO;
        BigDecimal thisWeekAmount = BigDecimal.ZERO;

        for (FinancialMovement mov : movements) {
            BigDecimal amount = mov.getNetAmount();
            LocalDate dueDate = mov.getDueDate();

            total = total.add(amount);

            if (dueDate.isBefore(today)) {
                overdue = overdue.add(amount);
            } else if (dueDate.isEqual(today)) {
                todayAmount = todayAmount.add(amount);
                thisWeekAmount = thisWeekAmount.add(amount); // O que vence hoje também vence nesta semana
            } else if (dueDate.isAfter(today) && dueDate.isBefore(endOfWeek.plusDays(1))) {
                thisWeekAmount = thisWeekAmount.add(amount);
            }
        }

        return new MovementSummaryDTO(total, overdue, todayAmount, thisWeekAmount);
    }

    private List<ChartDataDTO> generateChartData(List<FinancialMovement> movements) {
        // 1. Filtra estritamente o que já foi liquidado/efetivado (SETTLED)
        List<FinancialMovement> settledMovements = movements.stream()
                .filter(m -> m.getMovementStatus() == MovementStatus.SETTLED)
                .collect(Collectors.toList());

        // 2. Agrupa os registros pela data.
        // Priorizamos a data de pagamento real (paymentDate). Se for nula, usamos o vencimento original (dueDate).
        Map<LocalDate, List<FinancialMovement>> groupedByDate = settledMovements.stream()
                .filter(m -> m.getPaymentDate() != null || m.getDueDate() != null)
                .collect(Collectors.groupingBy(m ->
                        m.getPaymentDate() != null ? m.getPaymentDate() : m.getDueDate()
                ));

        List<ChartDataDTO> chartList = groupedByDate.entrySet().stream()
                // 3. Ordena os agrupamentos cronologicamente (do mais antigo para o mais novo)
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    List<FinancialMovement> dailyMovements = entry.getValue();

                    // Soma os Recebimentos efetivos (INCOME)
                    BigDecimal recebimentos = dailyMovements.stream()
                            .filter(m -> m.getMovementType() == MovementType.INCOME)
                            .map(m -> m.getNetAmount() != null ? m.getNetAmount() : BigDecimal.ZERO)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    // Soma os Pagamentos efetivos (EXPENSE)
                    BigDecimal pagamentos = dailyMovements.stream()
                            .filter(m -> m.getMovementType() == MovementType.EXPENSE)
                            .map(m -> m.getNetAmount() != null ? m.getNetAmount() : BigDecimal.ZERO)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    // Formata a data para ficar enxuta no eixo X do gráfico (Ex: "21/07")
                    String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM"));

                    return new ChartDataDTO(formattedDate, recebimentos, pagamentos);
                })
                .collect(Collectors.toList());

        // 4. Cria um estado vazio se não houver registros liquidados, garantindo que o gráfico não fique em branco
        if (chartList.isEmpty()) {
            chartList.add(new ChartDataDTO("Sem dados", BigDecimal.ZERO, BigDecimal.ZERO));
        }

        return chartList;
    }

}