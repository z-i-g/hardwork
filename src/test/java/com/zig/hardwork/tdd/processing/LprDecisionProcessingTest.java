package com.zig.hardwork.tdd.processing;

import com.zig.hardwork.tdd.dto.LprDecisionRq;
import com.zig.hardwork.tdd.dto.LprDecisionRs;
import com.zig.hardwork.tdd.entity.Application;
import com.zig.hardwork.tdd.entity.Participant;
import com.zig.hardwork.tdd.exception.ApplicationNotFoundException;
import com.zig.hardwork.tdd.exception.ApplicationStatusException;
import com.zig.hardwork.tdd.exception.LimitIdNotFoundException;
import com.zig.hardwork.tdd.service.ApplicationService;
import com.zig.hardwork.tdd.service.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LprDecisionProcessingTest {
    @InjectMocks
    private LprDecisionProcessing lprDecisionProcessing;
    @Mock
    private ApplicationService applicationService;
    @Mock
    private ParticipantService participantService;
    private final LprDecisionRq lprDecisionRq = new LprDecisionRq();
    private ContextApp contextApp;
    private Application application;
    private List<Participant> participants;

    @BeforeEach
    public void init() {
        lprDecisionRq.setAppNo("123");
        application = new Application();
        participants = new ArrayList<>();
        contextApp = new ContextApp(lprDecisionRq.getAppNo(), lprDecisionRq, application, participants);
    }

    @Test
    public void lprDecisionProcessingTest() {
        Mockito.when(applicationService.findByAppNo(lprDecisionRq.getAppNo())).thenReturn(application);
        Mockito.when(participantService.findAllByAppNo(lprDecisionRq.getAppNo())).thenReturn(participants);
        LprDecisionRs lprDecisionRs = lprDecisionProcessing.processing(lprDecisionRq);

        assertNotNull(lprDecisionRs);
        assertNotNull(lprDecisionRs.getStatus());
        assertNotNull(lprDecisionRs.getErrorCode());
    }

    @Test
    public void appStatusExceptionTest() {
        contextApp.getApplication().setStatus(44);
        Mockito.when(applicationService.findByAppNo(lprDecisionRq.getAppNo())).thenReturn(application);
        Mockito.when(participantService.findAllByAppNo(lprDecisionRq.getAppNo())).thenReturn(participants);
        Exception exception = assertThrows(ApplicationStatusException.class, () -> lprDecisionProcessing.processing(lprDecisionRq));

        assertEquals("Заявка находится в неподходящем статусе (44)", exception.getLocalizedMessage());
    }

    @Test
    public void whenPositiveLprDecisionAndLimitIdIsNull_thenThrow() {
        lprDecisionRq.setLprDecision("A");
        Mockito.when(applicationService.findByAppNo(lprDecisionRq.getAppNo())).thenReturn(application);
        Mockito.when(participantService.findAllByAppNo(lprDecisionRq.getAppNo())).thenReturn(participants);
        Exception exception = assertThrows(LimitIdNotFoundException.class, () -> lprDecisionProcessing.processing(lprDecisionRq));

        assertEquals("LimitId не может быть null при положительном решении", exception.getLocalizedMessage());
    }

    @Test
    public void applicationNotFoundExceptionTest() {
        Mockito.when(applicationService.findByAppNo(Mockito.anyString())).thenThrow(ApplicationNotFoundException.class);
        assertThrows(ApplicationNotFoundException.class, () -> lprDecisionProcessing.processing(lprDecisionRq));
    }
}
