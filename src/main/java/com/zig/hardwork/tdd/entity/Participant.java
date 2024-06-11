package com.zig.hardwork.tdd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Participant {
    @Id
    private Long id;

    private String appNo;

    private String lprDecision;

    private String refuseCode;
}