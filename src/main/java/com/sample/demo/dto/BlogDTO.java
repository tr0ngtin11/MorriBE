package com.sample.demo.dto;

import lombok.*;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogDTO {
    private Long blogId;
    private String title;
    private String description;
    private String content;
    private String imageUrl;
    private Date createAt;
}
