package com.zig.hardwork.designtdd.controller;

import com.zig.hardwork.designtdd.dto.ErrorCodes;
import com.zig.hardwork.designtdd.dto.LprDecisionRs;
import com.zig.hardwork.designtdd.dto.LprRsStatus;
import com.zig.hardwork.designtdd.exception.ApplicationNotFoundException;
import com.zig.hardwork.designtdd.exception.ApplicationStatusException;
import com.zig.hardwork.designtdd.exception.LimitIdNotFoundException;
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
                .status(LprRsStatus.ERROR.name())
                .errorCode(ErrorCodes.INVALID_MESSAGE.getCode())
                .message(String.format(ErrorCodes.INVALID_MESSAGE.getMessage(), errorsFields)).build();
        return ResponseEntity.ok(decisionRs);
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    protected ResponseEntity<Object> handleApplicationNotFoundException(ApplicationNotFoundException applicationNotFoundException, WebRequest webRequest) {
        LprDecisionRs decisionRs = LprDecisionRs.builder()
                .status(LprRsStatus.ERROR.name())
                .errorCode(ErrorCodes.APPLICATION_NOT_FOUND.getCode())
                .message(String.format(ErrorCodes.APPLICATION_NOT_FOUND.getMessage(), applicationNotFoundException.getLocalizedMessage())).build();
        return ResponseEntity.ok(decisionRs);
    }

    @ExceptionHandler(ApplicationStatusException.class)
    protected ResponseEntity<Object> handleApplicationStatusException(ApplicationStatusException applicationNotFoundException, WebRequest webRequest) {
        LprDecisionRs decisionRs = LprDecisionRs.builder()
                .status(LprRsStatus.ERROR.name())
                .errorCode(ErrorCodes.INVALID_APPLICATION_STATUS.getCode())
                .message(String.format(ErrorCodes.INVALID_APPLICATION_STATUS.getMessage(), applicationNotFoundException.getLocalizedMessage())).build();
        return ResponseEntity.ok(decisionRs);
    }

    @ExceptionHandler(LimitIdNotFoundException.class)
    protected ResponseEntity<Object> handleLimitIdNotFoundException(LimitIdNotFoundException applicationNotFoundException, WebRequest webRequest) {
        LprDecisionRs decisionRs = LprDecisionRs.builder()
                .status(LprRsStatus.ERROR.name())
                .errorCode(ErrorCodes.LIMIT_ID_NOT_FOUND.getCode())
                .message(ErrorCodes.LIMIT_ID_NOT_FOUND.getMessage()).build();
        return ResponseEntity.ok(decisionRs);
    }
}