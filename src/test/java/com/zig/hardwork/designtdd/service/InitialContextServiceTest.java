package com.zig.hardwork.designtdd.service;

import com.zig.hardwork.designtdd.dto.LprDecisionRq;
import com.zig.hardwork.designtdd.entity.Application;
import com.zig.hardwork.designtdd.processing.ContextApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class InitialContextServiceTest {
    @InjectMocks
    private InitialContextService initialContextService;
    @Mock
    private ApplicationService applicationService;
    @Mock
    private ParticipantService participantService;
    private LprDecisionRq lprDecisionRq;

    @BeforeEach
    public void init() {
        lprDecisionRq = new LprDecisionRq();
        lprDecisionRq.setAppNo("123");
    }

    @Test
    public void initialContextIsNotNullTest() {
        ContextApp contextApp = initialContextService.create(lprDecisionRq);

        assertNotNull(contextApp);
    }

    @Test
    public void applicationIsNotNullTest() {
        Mockito.when(applicationService.findByAppNo(Mockito.anyString())).thenReturn(new Application());
        ContextApp contextApp = initialContextService.create(lprDecisionRq);

        assertNotNull(contextApp.getApplication());
    }

    @Test
    public void participantsIsNotNullTest() {
        Mockito.when(participantService.findAllByAppNo(Mockito.anyString())).thenReturn(new ArrayList<>());
        ContextApp contextApp = initialContextService.create(lprDecisionRq);

        assertNotNull(contextApp.getParticipants());
    }


    @Test
    public void lprRequestIsNotNullTest() {
        ContextApp contextApp = initialContextService.create(lprDecisionRq);

        assertNotNull(contextApp.getLprDecisionRq());
    }


    @Test
    public void appNotIsNotNullTest() {
        ContextApp contextApp = initialContextService.create(lprDecisionRq);

        assertNotNull(contextApp.getAppNo());
        assertEquals("123", contextApp.getAppNo());
    }
}
