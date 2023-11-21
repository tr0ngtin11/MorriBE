package com.sample.demo.controller.advice;


import lombok.*;

import java.util.Date;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private Integer statusCode;
    private Date timestamp;
    private String message;
    private String Desccription;
}
