package com.zig.hardwork.tdd.processing;

import com.zig.hardwork.tdd.dto.LprDecisionRq;
import com.zig.hardwork.tdd.dto.LprDecisionRs;
import com.zig.hardwork.tdd.entity.Application;
import com.zig.hardwork.tdd.entity.Participant;
import com.zig.hardwork.tdd.exception.ApplicationStatusException;
import com.zig.hardwork.tdd.exception.LimitIdNotFoundException;
import com.zig.hardwork.tdd.service.ApplicationService;
import com.zig.hardwork.tdd.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class LprDecisionProcessing {
    private final ApplicationService applicationService;
    private final ParticipantService participantService;
    public LprDecisionRs processing(LprDecisionRq lprDecisionRq) {
        ContextApp contextApp = createContext(lprDecisionRq);
        businessValidate(contextApp);
        saveLprDecision(contextApp);

        LprDecisionRs lprDecisionRs = LprDecisionRs.builder()
                .status("OK")
                .errorCode("0")
                .build();
        return lprDecisionRs;
    }

    private ContextApp createContext(LprDecisionRq lprDecisionRq) {
        Application application = applicationService.findByAppNo(lprDecisionRq.getAppNo());
        List<Participant> participants = participantService.findAllByAppNo(lprDecisionRq.getAppNo());
        ContextApp contextApp = new ContextApp(lprDecisionRq.getAppNo(),
                lprDecisionRq, application, participants);
        return contextApp;
    }

    private void businessValidate(ContextApp contextApp) {
        if (isRefusalStatus.test(contextApp.getApplication().getStatus()))
            throw new ApplicationStatusException(String.format("Заявка находится в неподходящем статусе (%s)", contextApp.getApplication().getStatus()));

        if (isPositiveLprDecision.test(contextApp.getLprDecisionRq().getLprDecision()) && isNullLimitId.test(contextApp.getLprDecisionRq().getLimitId()))
            throw new LimitIdNotFoundException("LimitId не может быть null при положительном решении");
    }

    private final Predicate<Integer> isRefusalStatus = status -> Integer.valueOf(44).equals(status);

    private final Predicate<String> isPositiveLprDecision = "A"::equals;
    private final Predicate<String> isNullLimitId = Objects::isNull;

    private void saveLprDecision(ContextApp contextApp) {
        Application application = contextApp.getApplication();
        List<Participant> participants = contextApp.getParticipants();
        LprDecisionRq lprDecisionRq = contextApp.getLprDecisionRq();

        application.setLprDecision(lprDecisionRq.getLprDecision());
        application.setApprovedAmount(lprDecisionRq.getLprApprovedSum());
        if (isPositiveLprDecision.negate().test(lprDecisionRq.getLprDecision()))
            application.setRefuseCode("007");
        applicationService.save(application);

        participants.forEach(participant -> {
            participant.setLprDecision(lprDecisionRq.getLprDecision());
            if (isPositiveLprDecision.negate().test(lprDecisionRq.getLprDecision()))
                participant.setRefuseCode("007");
            participantService.save(participant);
        });
    }
}