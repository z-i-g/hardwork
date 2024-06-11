package com.zig.hardwork.designtdd.processing;

import com.zig.hardwork.designtdd.dto.LprDecisionRq;
import com.zig.hardwork.designtdd.dto.LprDecisionRs;
import com.zig.hardwork.designtdd.service.ApplicationService;
import com.zig.hardwork.designtdd.service.InitialContextService;
import com.zig.hardwork.designtdd.service.ParticipantService;
import com.zig.hardwork.designtdd.validation.BusinessValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class LprDecisionProcessingTest {
    @InjectMocks
    private LprDecisionProcessing lprDecisionProcessing;
    @Mock
    private InitialContextService initialContextService;
    @Mock
    private BusinessValidationService businessValidationService;
    @Mock
    private ApplicationService applicationService;
    @Mock
    private ParticipantService participantService;

    private final LprDecisionRq lprDecisionRq = new LprDecisionRq();

    @Test
    public void lprDecisionProcessingTest() {
        LprDecisionRs lprDecisionRs = lprDecisionProcessing.processing(lprDecisionRq);

        assertNotNull(lprDecisionRs);
        assertNotNull(lprDecisionRs.getStatus());
        assertNull(lprDecisionRs.getErrorCode());
    }
}
