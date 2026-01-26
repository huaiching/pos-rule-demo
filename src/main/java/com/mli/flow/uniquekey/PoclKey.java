package com.mli.flow.uniquekey;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PoclKey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "保單號碼")
    private String policyNo;

    @Schema(description = "關係碼")
    private String clientIdent;

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getClientIdent() {
        return clientIdent;
    }

    public void setClientIdent(String clientIdent) {
        this.clientIdent = clientIdent;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PoclKey poclKey = (PoclKey) o;
        return Objects.equals(policyNo, poclKey.policyNo) && Objects.equals(clientIdent, poclKey.clientIdent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNo, clientIdent);
    }
}
