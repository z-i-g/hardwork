package com.zig.hardwork.designtdd.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCodes {
    INVALID_MESSAGE("-1", "Невалидное сообщение: %s"),
    APPLICATION_NOT_FOUND("-2", "Заявка %s не найдена"),
    INVALID_APPLICATION_STATUS("-3", "Заявка находится в неподходящем статусе (%s)"),
    LIMIT_ID_NOT_FOUND("-4", "LimitId не может быть null при положительном решении");

    private final String code;
    private final String message;
}