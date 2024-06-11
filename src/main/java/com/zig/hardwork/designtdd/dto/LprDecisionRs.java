package com.zig.hardwork.designtdd.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class LprDecisionRs {
    @NotNull
    private String status;

    private String message;

    private String errorCode;
}