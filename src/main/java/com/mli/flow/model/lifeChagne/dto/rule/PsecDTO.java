package com.mli.flow.model.lifeChagne.dto.rule;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "核保訊息檔")
public class PsecDTO {
    @Schema(description = "核保訊息代碼")
    private String nbErrCode;
    @Schema(description = "核保訊息文字")
    private String nbErrDesc;
    @Schema(description = "核保等級")
    private String severity;
    @Schema(description = "核保訊息模板檔")
    private RuleMessageDTO ruleMessageDTO;

    public String getNbErrCode() {
        return nbErrCode;
    }

    public void setNbErrCode(String nbErrCode) {
        this.nbErrCode = nbErrCode;
    }

    public String getNbErrDesc() {
        return nbErrDesc;
    }

    public void setNbErrDesc(String nbErrDesc) {
        this.nbErrDesc = nbErrDesc;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public RuleMessageDTO getRuleMessageDTO() {
        return ruleMessageDTO;
    }

    public void setRuleMessageDTO(RuleMessageDTO ruleMessageDTO) {
        this.ruleMessageDTO = ruleMessageDTO;
    }
}
