package com.sample.demo.exception;

import java.util.NoSuchElementException;

public class SaleNotFoundException extends NoSuchElementException {
    public SaleNotFoundException(String message){
        super(message);
    }

    public SaleNotFoundException(){
        super("Not found your sale");
    }
}
