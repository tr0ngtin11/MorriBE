package com.sample.demo.service;

import com.sample.demo.dto.ProductDTO;
import com.sample.demo.exception.ProductNotFoundException;
import com.sample.demo.model.Product;
import com.sample.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    public List<Product> getAllProduct(Pageable pageable){
        Page<Product> page = productRepository.findAll(pageable);
        return page.getContent();
    }


    public Optional<Product> getProductByID(Long id){
        Optional<Product> product = productRepository.findById(id);
       return product;
    }
    public Product addProduct(ProductDTO product){

       return productRepository.save(new Product(product));
    }

    public Product updateProduct(ProductDTO product){
      return   productRepository.save(new Product(product));
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


    public List<Product> searchProducts(String keyword){

            return productRepository.findByProductNameContaining(keyword);
    }



}
