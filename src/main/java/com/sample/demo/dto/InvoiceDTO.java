package com.sample.demo.dto;

import com.sample.demo.model.Sale;
import com.sample.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    private Boolean isOnline;
    private Date createAt;
    private Long totalInvoice;
    //foreignKey
    private Long userId;
    private Long saleId;

}
