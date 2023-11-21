package com.sample.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sample.demo.dto.InvoiceDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  invoiceId;
    private Boolean isOnline;
    private Date createAt;
    private Long totalInvoice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "saleId")
    private Sale sale;
    //foreign key
    // invoice_detail
    @JsonIgnore
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceDetail> invoiceDetails;
    // Order
    @JsonIgnore
    @OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL)
    private Order order;



    public Invoice(InvoiceDTO invoiceDTO){
        this.isOnline = invoiceDTO.getIsOnline();
        this.createAt = invoiceDTO.getCreateAt();
        this.totalInvoice = invoiceDTO.getTotalInvoice();
        if (invoiceDTO.getUserId() != null) {
            this.user = new User();
            this.user.setUserId(invoiceDTO.getUserId());
        }
        if (invoiceDTO.getSaleId() != null) {
            this.sale = new Sale();
            this.sale.setSaleId(invoiceDTO.getSaleId());
        }
    }
}
