package com.sample.demo.controller;


import com.sample.demo.dto.PaymentDTO;
import com.sample.demo.dto.ResponseStringDTO;
import com.sample.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseStringDTO paymentInvoice(@RequestBody PaymentDTO paymentDTO){
        boolean result = paymentService.payment(paymentDTO);
        if(result){
            return ResponseStringDTO.builder()
                    .message("Payment successfully")
                    .build();
        }
        return ResponseStringDTO.builder()
                .message("Payment fail")
                .build();
    }
}
