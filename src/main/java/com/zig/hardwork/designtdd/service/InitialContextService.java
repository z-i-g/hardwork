package com.zig.hardwork.designtdd.service;

import com.zig.hardwork.designtdd.dto.LprDecisionRq;
import com.zig.hardwork.designtdd.entity.Application;
import com.zig.hardwork.designtdd.entity.Participant;
import com.zig.hardwork.designtdd.processing.ContextApp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InitialContextService {
    private final ApplicationService applicationService;
    private final ParticipantService participantService;
    public ContextApp create(LprDecisionRq lprDecisionRq) {
        Application application = applicationService.findByAppNo(lprDecisionRq.getAppNo());
        List<Participant> participants = participantService.findAllByAppNo(lprDecisionRq.getAppNo());
        ContextApp contextApp = new ContextApp(lprDecisionRq.getAppNo(),
                lprDecisionRq, application, participants);
        return contextApp;
    }
}