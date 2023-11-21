package com.sample.demo.repository;

import com.sample.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product,Long> {
    @Query(value = "SELECT * FROM Product WHERE product_name LIKE %:keyword% ", nativeQuery = true)
    List<Product> findByProductNameContaining(@Param("keyword") String keyword);
}
