package com.zig.hardwork.designtdd.controller;

import com.zig.hardwork.designtdd.dto.LprDecisionRq;
import com.zig.hardwork.designtdd.dto.LprDecisionRs;
import com.zig.hardwork.designtdd.processing.LprDecisionProcessing;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LprDecisionController {
    private final LprDecisionProcessing lprDecisionProcessing;

    @PostMapping("lpr-decision-processing")
    public LprDecisionRs lprDecisionProcessing(@RequestBody @Valid LprDecisionRq lprDecisionRq) {
        return lprDecisionProcessing.processing(lprDecisionRq);
    }
}