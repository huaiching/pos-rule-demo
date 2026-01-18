
package com.mli.flow.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "rscl")
@Schema(description = "客戶資料暫存檔")
public class RsclEntity {
    @Id
    @Schema(description = "客戶證號")
    @Column(name = "client_id")
    private String clientId;

    @Schema(description = "受理號碼")
    @Column(name = "receive_no")
    private String receiveNo;

    @Schema(description = "功能碼")
    @Column(name = "function_ind")
    private String functionInd;

    @Schema(description = "關係碼")
    @Column(name = "client_ident")
    private String clientIdent;

    @Schema(description = "姓名")
    @Column(name = "names")
    private String names;

    @Schema(description = "生日")
    @Column(name = "birth_date")
    private String birthDate;

    @Schema(description = "性別")
    @Column(name = "sex")
    private String sex;

    public RsclEntity() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getFunctionInd() {
        return functionInd;
    }

    public void setFunctionInd(String functionInd) {
        this.functionInd = functionInd;
    }

    public String getClientIdent() {
        return clientIdent;
    }

    public void setClientIdent(String clientIdent) {
        this.clientIdent = clientIdent;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RsclEntity that = (RsclEntity) o;
        return Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(clientId);
    }
}
