package com.mli.flow.model.lifeChagne.dto.rule;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "檢核規則檔")
public class RuleExpressionDTO {

    @Schema(description = "核保訊息代碼")
    private String nbErrCode;
    @Schema(description = "檢核群組編號")
    private String groupCode;
    @Schema(description = "規則模型")
    private String ruleModel;
    @Schema(description = "檢核規則")
    private String expression;

    public String getNbErrCode() {
        return nbErrCode;
    }

    public void setNbErrCode(String nbErrCode) {
        this.nbErrCode = nbErrCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getRuleModel() {
        return ruleModel;
    }

    public void setRuleModel(String ruleModel) {
        this.ruleModel = ruleModel;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
