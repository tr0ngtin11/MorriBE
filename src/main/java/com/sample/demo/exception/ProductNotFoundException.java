package com.sample.demo.exception;

import java.util.NoSuchElementException;

public class ProductNotFoundException extends NoSuchElementException {
    public ProductNotFoundException(String message){
        super(message);
    }

    public ProductNotFoundException(){
        super("Not found your product");
    }
}
