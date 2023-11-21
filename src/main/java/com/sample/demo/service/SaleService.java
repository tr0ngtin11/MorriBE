package com.sample.demo.service;


import com.sample.demo.dto.SaleDTO;
import com.sample.demo.model.Sale;
import com.sample.demo.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final Logger logger = LoggerFactory.getLogger(SaleService.class);

    public List<Sale> getAllSale(){
        return saleRepository.findAll();
    }


    public Optional<Sale> getSaleByID(Long id){
        Optional<Sale> Sale = saleRepository.findById(id);
        if(Sale == null){
            logger.error("Can't find any sale with Id: ");
        }
        return Sale;
    }
    public Sale addSale(SaleDTO Sale){
        return saleRepository.save(new Sale(Sale));
    }

    public Sale updateSale(SaleDTO Sale){
        return saleRepository.save(new Sale(Sale));
    }

    public void deleteSale(Long id){
        saleRepository.deleteById(id);
    }

    public void applySale(Long id) {
        List<Sale> listSales = this.getAllSale();
        Integer size = listSales.size();
        for (Sale currentE : listSales) {
            currentE.setIsCurrentApply(currentE.getSaleId().equals(id));
        }
    }
    

}
