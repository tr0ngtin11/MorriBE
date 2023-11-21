package com.sample.demo.dto;

import com.sample.demo.model.Size;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private Integer price;
    private String descriptionProduct;
    private String productUrl;
    private Size size;
    private List<Long> topping;
    private Integer quantity;
}
