package com.sample.demo.controller;

import com.sample.demo.dto.ProductDTO;
import com.sample.demo.exception.ProductNotFoundException;
import com.sample.demo.model.Product;
import com.sample.demo.repository.ProductRepository;
import com.sample.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;




    @GetMapping("/product")
    public ResponseEntity<Object> getAllProduct(Pageable pageable){
       List<Product> listProducts = productService.getAllProduct(pageable);
       return  ResponseEntity.ok(listProducts);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id){

        Optional<Product> product = productService.getProductByID(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Not found product with id: " + id);
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/product")
//    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Object> addProduct(@RequestBody ProductDTO productDTO){
        Product product = productService.addProduct(productDTO);

        return ResponseEntity.ok(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductDTO productDTO){
        Product product = productService.updateProduct(productDTO);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/product/{id}")
    public  ResponseEntity<Object> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("delete successfully");
    }

    @GetMapping("/product/search/{keyword}")
    public ResponseEntity<Object> searchProduct(@PathVariable String keyword){
        List<Product> listProductsBySearch = productService.searchProducts(keyword);
        return ResponseEntity.ok(listProductsBySearch);
    }
}
