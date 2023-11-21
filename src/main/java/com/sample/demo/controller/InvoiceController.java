package com.sample.demo.controller;

import com.sample.demo.model.Invoice;
import com.sample.demo.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class InvoiceController {
    private final InvoiceService invoiceService;
    @GetMapping("/user1/{userId}")
    public ResponseEntity<List<Invoice>> getAllInvoicesByUser(@PathVariable Long userId){
            List<Invoice> listInvoiceByUserId = invoiceService.getListInvoiceByUserId(userId);
            return ResponseEntity.ok(listInvoiceByUserId);
        }
}
