package com.mli.flow.controller;

import com.mli.flow.dto.CheckResultDTO;
import com.mli.flow.service.RuleMainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demoController")
@Tag(name = "Demo Controller", description = "Demo API")
public class DemoController {
    @Autowired
    private RuleMainService ruleMainService;

    @PostMapping("/demo/v1")
    @Operation(summary = "Demo API", description = "Demo API")
    public ResponseEntity<String> demo(@RequestBody String message) {
        String msg = "歡迎：" + message;
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/runPosRule/v1")
    @Operation(summary = "runPosRule API", description = "runPosRule API")
    public ResponseEntity<List<CheckResultDTO>> runPosRule() {
        return ResponseEntity.ok(ruleMainService.execute());
    }

}
