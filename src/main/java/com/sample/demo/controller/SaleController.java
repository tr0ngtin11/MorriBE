package com.sample.demo.controller;

import com.sample.demo.dto.SaleDTO;
import com.sample.demo.model.Sale;
import com.sample.demo.repository.SaleRepository;
import com.sample.demo.service.SaleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SaleController {
    private final SaleRepository saleRepository;
    private final SaleService saleService;

    @GetMapping("/sale")
    public ResponseEntity<Object> getAllSale(){
        List<Sale> listSales = saleService.getAllSale();
        return  ResponseEntity.ok(listSales);
    }

    @GetMapping("/sale/{id}")
    public ResponseEntity<Optional<Sale>> getSaleById(Long id){
        Optional<Sale> Sale = saleService.getSaleByID(id);
        return ResponseEntity.ok(Sale);
    }

    @PostMapping("/sale")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Object> addSale(@RequestBody SaleDTO saleDTO){
        Sale Sale = saleService.addSale(saleDTO);

        return ResponseEntity.ok(Sale);
    }

    @PutMapping("/sale/{id}")
    public ResponseEntity<Object> updateSale(@RequestBody SaleDTO saleDTO){
        Sale Sale = saleService.updateSale(saleDTO);
        return ResponseEntity.ok(Sale);
    }

    @DeleteMapping("/sale/{id}")
    public  ResponseEntity<Object> deleteSale(@PathVariable Long id){
        saleService.deleteSale(id);
        return ResponseEntity.ok("delete successfully");
    }
}
