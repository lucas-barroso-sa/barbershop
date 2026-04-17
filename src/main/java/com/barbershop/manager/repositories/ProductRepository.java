package com.barbershop.manager.repositories;

import com.barbershop.manager.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
