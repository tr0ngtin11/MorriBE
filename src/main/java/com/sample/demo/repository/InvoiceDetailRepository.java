package com.sample.demo.repository;

import com.sample.demo.model.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail,Long> {
}
