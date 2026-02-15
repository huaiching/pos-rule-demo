package com.mli.flow.model.lifeChagne.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "保單號碼 及 受理號碼")
public class PolicyNoAndReceiveNoDTO {
    @Schema(description = "保單號碼")
    private String policyNo;
    @Schema(description = "受理號碼")
    private String receiveNo;

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }
}
