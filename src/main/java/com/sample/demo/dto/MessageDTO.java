package com.sample.demo.dto;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private String to;
    private String subject;
    private String content;
    private String toName;
    private String from;
}
