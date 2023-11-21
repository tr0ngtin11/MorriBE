package com.sample.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sample.demo.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  productId;
    private String productName;
    private Integer price;
    private String descriptionProduct;
    private String productUrl;


    public Product(ProductDTO productDTO){
        this.productName = productDTO.getProductName();
        this.price = productDTO.getPrice();
        this.descriptionProduct = productDTO.getDescriptionProduct();
        this.productUrl = productDTO.getProductUrl();
    }


    @JsonIgnore
    public InvoiceDetail getInvoiceDetail() {
        return invoiceDetail;
    }

    @JsonIgnore
    public Category getCategory() {
        return category;
    }

    // foreign key
    @ManyToOne
    @JoinColumn(name = "invoiceDetailId")
    private InvoiceDetail invoiceDetail;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

}
