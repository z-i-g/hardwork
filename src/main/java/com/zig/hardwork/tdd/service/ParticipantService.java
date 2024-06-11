package com.zig.hardwork.tdd.service;

import com.zig.hardwork.tdd.entity.Participant;
import com.zig.hardwork.tdd.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public List<Participant> findAllByAppNo(String appNo) {
        return participantRepository.findAllByAppNo(appNo);
    }

    public void save(Participant participant) {
        participantRepository.save(participant);
    }
}