package com.sample.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.demo.dto.ProductDTO;
import com.sample.demo.exception.ProductNotFoundException;
import com.sample.demo.model.Product;
import com.sample.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper redisObjectMapper;

    public List<Product> getAllProduct(Pageable pageable){
        if(isExistCache("products")){
            logger.info("Get products from cache");
            List<Object> listCache =  redisTemplate.opsForList().range("products",0,-1);
            List<Product> productList = (List<Product>) (List<?>) listCache;
            return productList;
        }
        else{
            logger.info("Get products from db");
            Page<Product> page = productRepository.findAll(pageable);
            if (page.isEmpty()) {
                return (List<Product>) page.getContent();
            }
            //            redisTemplate.opsForValue().set("products",json);
            Product[] products = page.getContent().toArray(new Product[0]);
            redisTemplate.opsForList().rightPushAll("products", products);
            return page.getContent();
        }
    }


//    @Cacheable("products")
    public Optional<Product> getProductByID(Long id)  {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return product;
        }
        try {
            String json = redisObjectMapper.writeValueAsString(product.get());
            String key = String.valueOf(product.get().getProductId());
            redisTemplate.opsForValue().set(key,json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

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

    public boolean isExistCache(String key){
        int listSize = Objects.requireNonNull(redisTemplate.opsForList().size(key)).intValue();
        return listSize > 0;
    }



}
