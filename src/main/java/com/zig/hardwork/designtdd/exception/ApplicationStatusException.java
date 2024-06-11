package com.zig.hardwork.designtdd.exception;

public class ApplicationStatusException extends RuntimeException {
    public ApplicationStatusException(String errorMessage) {
        super(errorMessage);
    }
}