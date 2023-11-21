package com.sample.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long userId;
    private Long saleId;
    private List<ProductDTO> listProducts;
    private String note;
    private Boolean isOnline;



}
