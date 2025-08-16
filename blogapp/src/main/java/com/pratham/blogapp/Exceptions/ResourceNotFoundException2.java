package com.pratham.blogapp.Exceptions;

public class ResourceNotFoundException2 extends RuntimeException{


    String resourceName;
    String fieldName;
    String fieldValue;


    public ResourceNotFoundException2(String resourceName, String fieldName, String fieldValue) {
        super(String.format(" %s not found %s : %s" ,resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;

    }
}
