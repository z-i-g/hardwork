package com.zig.hardwork.tdd.exception;

public class ApplicationStatusException extends RuntimeException {
    public ApplicationStatusException(String errorMessage) {
        super(errorMessage);
    }
}