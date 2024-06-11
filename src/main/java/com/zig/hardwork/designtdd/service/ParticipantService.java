package com.zig.hardwork.designtdd.service;

import com.zig.hardwork.designtdd.dto.LprDecisionRq;
import com.zig.hardwork.designtdd.entity.Participant;
import com.zig.hardwork.designtdd.processing.ContextApp;
import com.zig.hardwork.designtdd.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public List<Participant> findAllByAppNo(String appNo) {
        return participantRepository.findAllByAppNo(appNo);
    }

    public void saveLprDecision(ContextApp contextApp) {
        List<Participant> participants = contextApp.getParticipants();
        LprDecisionRq lprDecisionRq = contextApp.getLprDecisionRq();
        participants.forEach(participant -> {
            participant.setLprDecision(lprDecisionRq.getLprDecision());
            if (isPositiveLprDecision.negate().test(lprDecisionRq.getLprDecision()))
                participant.setRefuseCode("007");
            participantRepository.save(participant);
        });
    }

    private final Predicate<String> isPositiveLprDecision = "A"::equals;
}