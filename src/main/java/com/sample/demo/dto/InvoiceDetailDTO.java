package com.sample.demo.dto;

import com.sample.demo.model.Invoice;
import com.sample.demo.model.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailDTO {
    private Size size;
    private String topping;
    private Integer price;
    private Integer quantity;
    private Long totalPrice; // (size+price) * quantity
    //foreignKey
    private Invoice invoice;

}
