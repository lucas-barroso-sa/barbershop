package com.barbershop.manager.models.enums;

public enum EventType {
    SERVICE_PAYMENT,  // Pagamento de um agendamento (Corte, Barba)
    PRODUCT_SALE,     // Venda avulsa no PDV (Pomada, Cerveja)
    COST_CENTER,      // Despesa ou entrada categorizada (Conta de luz, Aluguel)
    PAYROLL             //repasse
}
