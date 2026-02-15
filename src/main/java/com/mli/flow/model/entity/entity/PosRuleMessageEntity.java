
package com.mli.flow.model.entity.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pos_rule_message")
@Schema(description = "客戶資料檔")
public class PosRuleMessageEntity {
    @Id
    @Schema(description = "核保訊息代碼")
    @Column(name = "nb_err_code")
    private String nbErrCode;

    @Schema(description = "訊息類型")
    @Column(name = "message_type")
    private String messageType;

    @Schema(description = "訊息模板")
    @Column(name = "message_template")
    private String messageTemplate;

    public PosRuleMessageEntity() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PosRuleMessageEntity that = (PosRuleMessageEntity) o;
        return Objects.equals(nbErrCode, that.nbErrCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nbErrCode);
    }
}
