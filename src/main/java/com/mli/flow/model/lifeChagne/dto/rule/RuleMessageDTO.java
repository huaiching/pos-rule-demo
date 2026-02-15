package com.mli.flow.model.lifeChagne.dto.rule;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "核保訊息模板檔")
public class RuleMessageDTO {
    @Schema(description = "核保訊息代碼")
    private String nbErrCode;
    @Schema(description = "訊息類型")
    private String messageType;
    @Schema(description = "訊息模板")
    private String messageTemplate;

    public String getNbErrCode() {
        return nbErrCode;
    }

    public void setNbErrCode(String nbErrCode) {
        this.nbErrCode = nbErrCode;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    public void setMessageTemplate(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }
}
