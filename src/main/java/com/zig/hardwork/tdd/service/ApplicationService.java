package com.zig.hardwork.tdd.service;

import com.zig.hardwork.tdd.entity.Application;
import com.zig.hardwork.tdd.exception.ApplicationNotFoundException;
import com.zig.hardwork.tdd.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    private ApplicationRepository applicationRepository;

    public Application findByAppNo(String appNo) {
        return applicationRepository.findByAppNo(appNo).orElseThrow(() -> new ApplicationNotFoundException(String.format("Заявка %s не найдена", appNo)));
    }

    public void save(Application application) {
        applicationRepository.save(application);
    }
}