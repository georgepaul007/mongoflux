package com.example.mobileSales.exception;

public class BrandExistException extends Exception {
    public BrandExistException() {
        super("Brand already exists!");
    }
}
