package com.sample.demo.model;

import com.sample.demo.dto.BlogDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogId;
    private String title;
    private String description;
    private String content;
    private String imageUrl;
    private Date createAt;

    // foreign key
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


    public Blog(BlogDTO blogDTO){
        this.title = blogDTO.getTitle();
        this.description = blogDTO.getDescription();
        this.content = blogDTO.getContent();
        this.imageUrl = blogDTO.getImageUrl();
        this.createAt = blogDTO.getCreateAt();
    }

}
