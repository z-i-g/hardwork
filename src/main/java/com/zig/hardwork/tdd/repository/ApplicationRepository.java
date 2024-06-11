package com.zig.hardwork.tdd.repository;

import com.zig.hardwork.tdd.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByAppNo(String appNo);
}