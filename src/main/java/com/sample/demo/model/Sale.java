package com.sample.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sample.demo.dto.SaleDTO;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  saleId;
    private String saleName;
    private Integer percentSale;
    private Boolean isCurrentApply;

    //foreign key

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoiceId")
    private Sale sale;

    public Sale(SaleDTO saleDTO){
        this.saleName = saleDTO.getSaleName();
        this.percentSale = saleDTO.getPercentSale();
        this.isCurrentApply = saleDTO.getIsCurrentApply();

    }
}
