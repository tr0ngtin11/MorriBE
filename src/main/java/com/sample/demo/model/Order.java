package com.sample.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  orderId;
    private String Username;
    private String phoneNumber;
    private String email;
    private String paymentMethod;
    private Status status;
    private String note;


    @OneToOne
    @JoinColumn(name = "invoiceId")
    private Invoice invoice;

}
