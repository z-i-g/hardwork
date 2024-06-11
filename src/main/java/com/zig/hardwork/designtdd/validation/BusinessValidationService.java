package com.zig.hardwork.designtdd.validation;

import com.zig.hardwork.designtdd.exception.ApplicationStatusException;
import com.zig.hardwork.designtdd.exception.LimitIdNotFoundException;
import com.zig.hardwork.designtdd.processing.ContextApp;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Predicate;

@Service
public class BusinessValidationService {
    public void validate(ContextApp contextApp) {
        if (isRefusalStatus.test(contextApp.getApplication().getStatus()))
            throw new ApplicationStatusException(String.format("Заявка находится в неподходящем статусе (%s)", contextApp.getApplication().getStatus()));

        if (isPositiveLprDecision.test(contextApp.getLprDecisionRq().getLprDecision()) && isNullLimitId.test(contextApp.getLprDecisionRq().getLimitId()))
            throw new LimitIdNotFoundException("LimitId не может быть null при положительном решении");
    }

    private final Predicate<Integer> isRefusalStatus = status -> Integer.valueOf(44).equals(status);

    private final Predicate<String> isPositiveLprDecision = "A"::equals;

    private final Predicate<String> isNullLimitId = Objects::isNull;
}