package com.sample.demo.service;

import com.sample.demo.dto.InvoiceDTO;
import com.sample.demo.model.Invoice;
import com.sample.demo.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    public List<Invoice> getAllInvoice(){
        return  invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceByID(Long id){
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if(invoice == null){
            logger.error("Can't find any invoice with Id: ");
        }
        return invoice;
    }

    public Optional<Invoice> getInvoiceLastes(){
       return invoiceRepository.findTopByInvoiceIdDescWithLimit();
    }
    public Invoice addInvoice(InvoiceDTO invoice){

        return invoiceRepository.save(new Invoice(invoice));
    }

    public Invoice updateInvoice(InvoiceDTO invoice){
        return   invoiceRepository.save(new Invoice(invoice));
    }

    public void deleteInvoice(Long id){
        invoiceRepository.deleteById(id);
    }

    public List<Invoice> getListInvoiceByUserId(Long userId){
        return invoiceRepository.findAllByUserId(userId);
    }


}
