package com.example.mobileSales.exception;

public class MobileExistException extends Exception {
    public MobileExistException() {
        super("Mobile already exists");
    }
}
