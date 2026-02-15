package com.mli.flow.model.lifeChagne.dto.rule;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "檢核規則相關檔案")
public class RuleTableDTO {
    @Schema(description = "核保訊息檔")
    List<PsecDTO> psecList;
    @Schema(description = "檢核規則檔")
    List<RuleExpressionDTO> ruleExpressionList;

    public List<PsecDTO> getPsecList() {
        return psecList;
    }

    public void setPsecList(List<PsecDTO> psecList) {
        this.psecList = psecList;
    }

    public List<RuleExpressionDTO> getRuleExpressionList() {
        return ruleExpressionList;
    }

    public void setRuleExpressionList(List<RuleExpressionDTO> ruleExpressionList) {
        this.ruleExpressionList = ruleExpressionList;
    }
}
