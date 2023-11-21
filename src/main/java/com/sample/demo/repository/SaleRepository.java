package com.sample.demo.repository;

import com.sample.demo.model.Product;
import com.sample.demo.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
