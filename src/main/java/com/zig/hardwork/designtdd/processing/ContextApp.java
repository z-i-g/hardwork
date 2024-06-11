package com.zig.hardwork.designtdd.processing;

import com.zig.hardwork.designtdd.dto.LprDecisionRq;
import com.zig.hardwork.designtdd.entity.Application;
import com.zig.hardwork.designtdd.entity.Participant;
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