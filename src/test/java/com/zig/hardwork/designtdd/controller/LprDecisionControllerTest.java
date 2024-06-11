package com.zig.hardwork.designtdd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zig.hardwork.designtdd.dto.LprDecisionRq;
import com.zig.hardwork.designtdd.exception.ApplicationNotFoundException;
import com.zig.hardwork.designtdd.exception.ApplicationStatusException;
import com.zig.hardwork.designtdd.exception.LimitIdNotFoundException;
import com.zig.hardwork.designtdd.processing.LprDecisionProcessing;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(LprDecisionController.class)
class LprDecisionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LprDecisionProcessing lprDecisionProcessing;

    @Test
    public void lprDecisionControllerIsOkTest() throws Exception {
        LprDecisionRq lprRq = new LprDecisionRq();

        mockMvc.perform(post("/lpr-decision-processing")
                .content(objectMapper.writeValueAsString(lprRq))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void lprDecisionControllerAdviceTestWithoutMessageUid() throws Exception {
        LprDecisionRq lprRq = new LprDecisionRq();
        lprRq.setAppNo("123");
        lprRq.setLprLogin("lprLogin");
        lprRq.setLimitId("limitId");
        lprRq.setLprDecision("A");
        lprRq.setLprApprovedSum(BigDecimal.valueOf(4578));

        mockMvc.perform(post("/lpr-decision-processing")
                        .content(objectMapper.writeValueAsString(lprRq))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status").value("ERROR"))
                .andExpect(jsonPath("$.errorCode").value("-1"))
                .andExpect(jsonPath("$.message").value("Невалидное сообщение: messageUid"));
    }

    @Test
    public void lprDecisionControllerAdviceTestWithoutAppNo() throws Exception {
        LprDecisionRq lprRq = new LprDecisionRq();
        lprRq.setMessageUid("msgId");
        lprRq.setLprLogin("lprLogin");
        lprRq.setLimitId("limitId");
        lprRq.setLprDecision("A");
        lprRq.setLprApprovedSum(BigDecimal.valueOf(4578));

        mockMvc.perform(post("/lpr-decision-processing")
                        .content(objectMapper.writeValueAsString(lprRq))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status").value("ERROR"))
                .andExpect(jsonPath("$.errorCode").value("-1"))
                .andExpect(jsonPath("$.message").value("Невалидное сообщение: appNo"));
    }

    @Test
    public void lprDecisionControllerAdviceTestWithoutLprLogin() throws Exception {
        LprDecisionRq lprRq = new LprDecisionRq();
        lprRq.setMessageUid("msgId");
        lprRq.setAppNo("123");
        lprRq.setLimitId("limitId");
        lprRq.setLprDecision("A");
        lprRq.setLprApprovedSum(BigDecimal.valueOf(4578));

        mockMvc.perform(post("/lpr-decision-processing")
                        .content(objectMapper.writeValueAsString(lprRq))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status").value("ERROR"))
                .andExpect(jsonPath("$.errorCode").value("-1"))
                .andExpect(jsonPath("$.message").value("Невалидное сообщение: lprLogin"));
    }

    @Test
    public void lprDecisionControllerAdviceTestWithoutLprDecision() throws Exception {
        LprDecisionRq lprRq = new LprDecisionRq();
        lprRq.setMessageUid("msgId");
        lprRq.setAppNo("123");
        lprRq.setLprLogin("lprLogin");
        lprRq.setLimitId("limitId");
        lprRq.setLprApprovedSum(BigDecimal.valueOf(4578));

        mockMvc.perform(post("/lpr-decision-processing")
                        .content(objectMapper.writeValueAsString(lprRq))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status").value("ERROR"))
                .andExpect(jsonPath("$.errorCode").value("-1"))
                .andExpect(jsonPath("$.message").value("Невалидное сообщение: lprDecision"));
    }

    @Test
    public void lprDecisionControllerAdviceTestWithoutLprApprovedSum() throws Exception {
        LprDecisionRq lprRq = new LprDecisionRq();
        lprRq.setMessageUid("msgId");
        lprRq.setAppNo("123");
        lprRq.setLprLogin("lprLogin");
        lprRq.setLimitId("limitId");
        lprRq.setLprDecision("A");

        mockMvc.perform(post("/lpr-decision-processing")
                        .content(objectMapper.writeValueAsString(lprRq))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status").value("ERROR"))
                .andExpect(jsonPath("$.errorCode").value("-1"))
                .andExpect(jsonPath("$.message").value("Невалидное сообщение: lprApprovedSum"));
    }

    @Test
    public void applicationNotFoundExceptionHandleTest() throws Exception {
        LprDecisionRq lprRq = new LprDecisionRq();
        lprRq.setMessageUid("msgId");
        lprRq.setAppNo("123");
        lprRq.setLprLogin("lprLogin");
        lprRq.setLimitId("limitId");
        lprRq.setLprDecision("A");
        lprRq.setLprApprovedSum(BigDecimal.valueOf(4578));
        Mockito.when(lprDecisionProcessing.processing(Mockito.any())).thenThrow(ApplicationNotFoundException.class);

        mockMvc.perform(post("/lpr-decision-processing")
                        .content(objectMapper.writeValueAsString(lprRq))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status").value("ERROR"))
                .andExpect(jsonPath("$.errorCode").value("-2"));
    }

    @Test
    public void ApplicationStatusExceptionHandleTest() throws Exception {
        LprDecisionRq lprRq = new LprDecisionRq();
        lprRq.setMessageUid("msgId");
        lprRq.setAppNo("123");
        lprRq.setLprLogin("lprLogin");
        lprRq.setLimitId("limitId");
        lprRq.setLprDecision("A");
        lprRq.setLprApprovedSum(BigDecimal.valueOf(4578));
        Mockito.when(lprDecisionProcessing.processing(Mockito.any())).thenThrow(ApplicationStatusException.class);

        mockMvc.perform(post("/lpr-decision-processing")
                        .content(objectMapper.writeValueAsString(lprRq))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status").value("ERROR"))
                .andExpect(jsonPath("$.errorCode").value("-3"));
    }

    @Test
    public void LimitIdNotFoundExceptionHandleTest() throws Exception {
        LprDecisionRq lprRq = new LprDecisionRq();
        lprRq.setMessageUid("msgId");
        lprRq.setAppNo("123");
        lprRq.setLprLogin("lprLogin");
        lprRq.setLimitId("limitId");
        lprRq.setLprDecision("A");
        lprRq.setLprApprovedSum(BigDecimal.valueOf(4578));
        Mockito.when(lprDecisionProcessing.processing(Mockito.any())).thenThrow(LimitIdNotFoundException.class);

        mockMvc.perform(post("/lpr-decision-processing")
                        .content(objectMapper.writeValueAsString(lprRq))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status").value("ERROR"))
                .andExpect(jsonPath("$.errorCode").value("-4"));
    }
}