package com.zig.hardwork.designtdd.validation;

import com.zig.hardwork.designtdd.dto.LprDecisionRq;
import com.zig.hardwork.designtdd.entity.Application;
import com.zig.hardwork.designtdd.entity.Participant;
import com.zig.hardwork.designtdd.exception.ApplicationStatusException;
import com.zig.hardwork.designtdd.exception.LimitIdNotFoundException;
import com.zig.hardwork.designtdd.processing.ContextApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BusinessValidationTest {
    @InjectMocks
    private BusinessValidationService businessValidationService;
    private final LprDecisionRq lprDecisionRq = new LprDecisionRq();
    private ContextApp contextApp;

    @BeforeEach
    public void init() {
        lprDecisionRq.setAppNo("123");
        Application application = new Application();
        List<Participant> participants = new ArrayList<>();
        contextApp = new ContextApp(lprDecisionRq.getAppNo(), lprDecisionRq, application, participants);
    }

    @Test
    public void appStatusExceptionTest() {
        contextApp.getApplication().setStatus(44);
        Exception exception = assertThrows(ApplicationStatusException.class, () -> {
            businessValidationService.validate(contextApp);
        });

        assertEquals("Заявка находится в неподходящем статусе (44)", exception.getLocalizedMessage());
    }

    @Test
    public void whenPositiveLprDecisionAndLimitIdIsNull_thenThrow() {
        lprDecisionRq.setLprDecision("A");
        Exception exception = assertThrows(LimitIdNotFoundException.class, () -> {
            businessValidationService.validate(contextApp);
        });

        assertEquals("LimitId не может быть null при положительном решении", exception.getLocalizedMessage());
    }


}