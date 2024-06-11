package com.zig.hardwork.tdd.exception;

public class LimitIdNotFoundException extends RuntimeException {
    public LimitIdNotFoundException(String message) {
        super(message);
    }
}