package com.zig.hardwork.designtdd.service;

import com.zig.hardwork.designtdd.dto.LprDecisionRq;
import com.zig.hardwork.designtdd.entity.Application;
import com.zig.hardwork.designtdd.exception.ApplicationNotFoundException;
import com.zig.hardwork.designtdd.processing.ContextApp;
import com.zig.hardwork.designtdd.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class ApplicationService {
    private ApplicationRepository applicationRepository;

    public Application findByAppNo(String appNo) {
        return applicationRepository.findByAppNo(appNo).orElseThrow(() -> new ApplicationNotFoundException(String.format("Заявка %s не найдена", appNo)));
    }

    public void saveLprDecision(ContextApp contextApp) {
        Application application = contextApp.getApplication();
        LprDecisionRq lprDecisionRq = contextApp.getLprDecisionRq();

        application.setLprDecision(lprDecisionRq.getLprDecision());
        application.setApprovedAmount(lprDecisionRq.getLprApprovedSum());
        if (isPositiveLprDecision.negate().test(lprDecisionRq.getLprDecision()))
            application.setRefuseCode("007");
        applicationRepository.save(application);
    }

    private final Predicate<String> isPositiveLprDecision = "A"::equals;

}