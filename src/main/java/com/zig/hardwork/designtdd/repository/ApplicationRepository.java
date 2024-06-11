package com.zig.hardwork.designtdd.repository;

import com.zig.hardwork.designtdd.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByAppNo(String appNo);
}