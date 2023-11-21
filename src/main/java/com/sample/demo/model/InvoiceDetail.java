package com.sample.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoiceDetail")
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  invoiceDetailId;
    private Size size;
    private String topping;
    private Integer price;
    private Integer quantity;
    private Long totalPrice; // (size+price) * quantity

    //foreign key
    //Invoice
    @ManyToOne
    @JoinColumn(name = "invoiceId")
    private Invoice invoice;

    @OneToMany(mappedBy = "invoiceDetail", cascade = CascadeType.ALL)
    private List<Product> product;
}
