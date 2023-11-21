package com.sample.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  categoryId;
    private String categoryName;

    // foreign key
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> product;
}
