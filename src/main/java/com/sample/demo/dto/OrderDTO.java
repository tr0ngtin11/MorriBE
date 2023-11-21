package com.sample.demo.dto;

import com.sample.demo.model.Invoice;
import com.sample.demo.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
        private String Username;
        private String phoneNumber;
        private String email;
        private String paymentMethod;
        private Status status;
        private String note;
    //foreignKey
    private Invoice invoice;

}
