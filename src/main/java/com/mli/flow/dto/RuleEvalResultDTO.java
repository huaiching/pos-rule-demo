package com.mli.flow.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Schema(description = "規則評估結果")
public class RuleEvalResultDTO {
    @Schema(description = "是否命中")
    private Boolean matched;
    @Schema(description = "簡單的檢核變數")
    private Map<String, Object> simpleContext;
    @Schema(description = "複雜的檢核變數")
    private List<Map<String, Object>> complexContexts;

    public RuleEvalResultDTO() {
        this.matched = false;
        this.simpleContext = new HashMap<>();
        this.complexContexts = new ArrayList<>();
    }

    public Boolean getMatched() {
        return matched;
    }

    public void setMatched(Boolean matched) {
        this.matched = matched;
    }

    public Map<String, Object> getSimpleContext() {
        return simpleContext;
    }

    public void setSimpleContext(Map<String, Object> simpleContext) {
        this.simpleContext = simpleContext;
    }

    public List<Map<String, Object>> getComplexContexts() {
        return complexContexts;
    }

    public void setComplexContexts(List<Map<String, Object>> complexContexts) {
        this.complexContexts = complexContexts;
    }
}
