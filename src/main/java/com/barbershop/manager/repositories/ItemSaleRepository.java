package com.barbershop.manager.repositories;

import com.barbershop.manager.models.entities.ItemSale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSaleRepository extends JpaRepository<ItemSale, Long> {
}
