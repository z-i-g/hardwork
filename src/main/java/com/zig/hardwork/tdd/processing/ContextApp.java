package com.zig.hardwork.tdd.processing;

import com.zig.hardwork.tdd.dto.LprDecisionRq;
import com.zig.hardwork.tdd.entity.Application;
import com.zig.hardwork.tdd.entity.Participant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ContextApp {
    private final String appNo;
    private final LprDecisionRq lprDecisionRq;
    private final Application application;
    private final List<Participant> participants;
}