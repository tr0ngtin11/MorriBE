package com.sample.demo.exception;

import java.util.NoSuchElementException;

public class BlogNotFoundException extends NoSuchElementException {
    public BlogNotFoundException(String message){
        super(message);
    }

    public BlogNotFoundException(){
        super("Not found your blog");
    }
}
