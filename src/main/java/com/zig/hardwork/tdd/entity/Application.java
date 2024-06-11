package com.zig.hardwork.tdd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Application {
    @Id
    private Long id;

    private Integer status;

    private String appNo;

    private String lprDecision;

    private BigDecimal approvedAmount;

    private String refuseCode;
}
