package com.zig.hardwork.tdd.controller;

import com.zig.hardwork.tdd.dto.LprDecisionRs;
import com.zig.hardwork.tdd.exception.ApplicationNotFoundException;
import com.zig.hardwork.tdd.exception.ApplicationStatusException;
import com.zig.hardwork.tdd.exception.LimitIdNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class LprDecisionControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException,
                                                                  HttpHeaders headers, HttpStatusCode statusCode,
                                                                  WebRequest request) {
        String errorsFields = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(", "));
        LprDecisionRs decisionRs = LprDecisionRs.builder()
                .status("ERROR")
                .errorCode("-1")
                .message("Невалидное сообщение: " + errorsFields).build();
        return ResponseEntity.ok(decisionRs);
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    protected ResponseEntity<Object> handleApplicationNotFoundException(ApplicationNotFoundException applicationNotFoundException, WebRequest webRequest) {
        LprDecisionRs decisionRs = LprDecisionRs.builder()
                .status("ERROR")
                .errorCode("-2")
                .message(applicationNotFoundException.getLocalizedMessage()).build();
        return ResponseEntity.ok(decisionRs);
    }

    @ExceptionHandler(ApplicationStatusException.class)
    protected ResponseEntity<Object> handleApplicationStatusException(ApplicationStatusException applicationNotFoundException, WebRequest webRequest) {
        LprDecisionRs decisionRs = LprDecisionRs.builder()
                .status("ERROR")
                .errorCode("-3")
                .message(applicationNotFoundException.getLocalizedMessage()).build();
        return ResponseEntity.ok(decisionRs);
    }

    @ExceptionHandler(LimitIdNotFoundException.class)
    protected ResponseEntity<Object> handleLimitIdNotFoundException(LimitIdNotFoundException applicationNotFoundException, WebRequest webRequest) {
        LprDecisionRs decisionRs = LprDecisionRs.builder()
                .status("ERROR")
                .errorCode("-4")
                .message(applicationNotFoundException.getLocalizedMessage()).build();
        return ResponseEntity.ok(decisionRs);
    }

}