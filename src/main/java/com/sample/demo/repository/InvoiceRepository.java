package com.sample.demo.repository;

import com.sample.demo.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {


    @Query(value = "SELECT * FROM Invoice ORDER BY invoice_id DESC LIMIT 1", nativeQuery = true)
    Optional<Invoice> findTopByInvoiceIdDescWithLimit();

    @Query(value = "SELECT * FROM Invoice WHERE :userId " , nativeQuery = true)
    List<Invoice> findAllByUserId(@Param("userId") Long userId);
}
