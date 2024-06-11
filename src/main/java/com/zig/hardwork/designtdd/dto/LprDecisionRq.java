package com.zig.hardwork.designtdd.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class LprDecisionRq {
    @NotNull
    @Size(max = 36)
    private String messageUid;

    @NotNull
    @Size(max = 20)
    private String appNo;

    @NotNull
    @Size(max = 40)
    private String lprLogin;

    @Size(max = 32)
    private String limitId;

    @NotNull
    @Size(max = 1)
    private String lprDecision;

    @NotNull
    private BigDecimal lprApprovedSum;

    @Size(max = 255)
    private String lprComment;
}