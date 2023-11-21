package com.sample.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private String saleName;
    private Integer percentSale;
    private Boolean isCurrentApply;
}
