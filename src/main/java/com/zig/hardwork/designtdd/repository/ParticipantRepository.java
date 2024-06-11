package com.zig.hardwork.designtdd.repository;

import com.zig.hardwork.designtdd.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findAllByAppNo(String appNo);
}