package com.zig.hardwork.designtdd.exception;

public class LimitIdNotFoundException extends RuntimeException {
    public LimitIdNotFoundException(String message) {
        super(message);
    }
}