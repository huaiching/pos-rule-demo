package com.mli.flow.entity;

import com.mli.flow.uniquekey.ChswKey;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "chsw")
@IdClass(ChswKey.class)
@Schema(description = "契變狀態表")
public class ChswEntity {
    @Id
    @Schema(description = "保單號碼")
    @Column(name = "policy_no")
    private String policyNo;

    @Id
    @Schema(description = "受理號碼")
    @Column(name = "receive_no")
    private String receiveNo;

    @Schema(description = "受理日期")
    @Column(name = "receive_date")
    private String receiveDate;

    @Schema(description = "變更生效日")
    @Column(name = "change_date")
    private String changeDate;

    @Schema(description = "變更選項 : 0.首期契變 / 1.一般契變 / 2.復效")
    @Column(name = "change_type")
    private String changeType;

    @Schema(description = "基本變更 : Y.有 / N.無")
    @Column(name = "basic_ind")
    private String basicInd;

    @Schema(description = "關係人變更 : Y.有 / N.無")
    @Column(name = "client_ind")
    private String clientInd;

    @Schema(description = "保障變更 : Y.有 / N.無")
    @Column(name = "coverage_ind")
    private String coverageInd;

    public ChswEntity() {
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

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getBasicInd() {
        return basicInd;
    }

    public void setBasicInd(String basicInd) {
        this.basicInd = basicInd;
    }

    public String getClientInd() {
        return clientInd;
    }

    public void setClientInd(String clientInd) {
        this.clientInd = clientInd;
    }

    public String getCoverageInd() {
        return coverageInd;
    }

    public void setCoverageInd(String coverageInd) {
        this.coverageInd = coverageInd;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChswEntity that = (ChswEntity) o;
        return Objects.equals(policyNo, that.policyNo) && Objects.equals(receiveNo, that.receiveNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNo, receiveNo);
    }
}
