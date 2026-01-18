package com.mli.flow.entity;

import com.mli.flow.uniquekey.AddrKey;
import com.mli.flow.uniquekey.PoclKey;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pocl")
@IdClass(PoclKey.class)
@Schema(description = "保單關係檔")
public class PoclEntity {
    @Id
    @Schema(description = "保單號碼")
    @Column(name = "policy_no")
    private String policyNo;

    @Id
    @Schema(description = "關係碼")
    @Column(name = "client_ident")
    private String clientIdent;

    @Schema(description = "客戶證號")
    @Column(name = "client_id")
    private String clientId;

    public PoclEntity() {
    }

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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PoclEntity that = (PoclEntity) o;
        return Objects.equals(policyNo, that.policyNo) && Objects.equals(clientIdent, that.clientIdent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNo, clientIdent);
    }
}
