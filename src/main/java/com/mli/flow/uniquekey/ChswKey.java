package com.mli.flow.uniquekey;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChswKey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "保單號碼")
    private String policyNo;

    @Schema(description = "受理號碼")
    private String receiveNo;

    public ChswKey() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChswKey chswKey = (ChswKey) o;
        return Objects.equals(policyNo, chswKey.policyNo) && Objects.equals(receiveNo, chswKey.receiveNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNo, receiveNo);
    }
}
