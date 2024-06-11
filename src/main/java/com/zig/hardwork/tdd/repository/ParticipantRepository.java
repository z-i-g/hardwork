package com.zig.hardwork.tdd.repository;

import com.zig.hardwork.tdd.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findAllByAppNo(String appNo);
}