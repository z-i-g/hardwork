package com.zig.hardwork.designtdd.processing;

import com.zig.hardwork.designtdd.dto.LprDecisionRq;
import com.zig.hardwork.designtdd.dto.LprDecisionRs;
import com.zig.hardwork.designtdd.dto.LprRsStatus;
import com.zig.hardwork.designtdd.service.ApplicationService;
import com.zig.hardwork.designtdd.service.InitialContextService;
import com.zig.hardwork.designtdd.service.ParticipantService;
import com.zig.hardwork.designtdd.validation.BusinessValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LprDecisionProcessing {
    private final InitialContextService initialContextService;
    private final BusinessValidationService businessValidationService;
    private final ApplicationService applicationService;
    private final ParticipantService participantService;

    public LprDecisionRs processing(LprDecisionRq lprDecisionRq) {
        ContextApp contextApp = initialContextService.create(lprDecisionRq);
        businessValidationService.validate(contextApp);
        applicationService.saveLprDecision(contextApp);
        participantService.saveLprDecision(contextApp);

        return LprDecisionRs.builder()
                .status(LprRsStatus.OK.name())
                .build();
    }
}