package com.mli.flow.model.lifeChagne.controller;

import com.mli.flow.model.lifeChagne.dto.engine.CheckResultDTO;
import com.mli.flow.model.lifeChagne.service.RuleMainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/policyRuleCheckController")
@Tag(name = "Policy Rule Chack Controller", description = "(保單) 核保訊息檢核 API")
public class PolicyRuleCheckController {
    @Autowired
    private RuleMainService ruleMainService;

    @PostMapping("/runPosRule/v1")
    @Operation(summary = "runPosRule API", description = "runPosRule API")
    public ResponseEntity<List<CheckResultDTO>> runPosRule(String policyNo, String receiveNo) {
        return ResponseEntity.ok(ruleMainService.execute(policyNo, receiveNo));
    }

}
