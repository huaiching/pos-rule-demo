package com.mli.flow.entity;

import com.mli.flow.uniquekey.RscoKey;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rsco")
@IdClass(RscoKey.class)
@Schema(description = "壽險保障暫存檔")
public class RscoEntity {
    @Id
    @Schema(description = "保單號碼")
    @Column(name = "policy_no")
    private String policyNo;

    @Id
    @Schema(description = "保障序號")
    @Column(name = "coverage_no")
    private Integer coverageNo;

    @Schema(description = "受理號碼")
    @Column(name = "receive_no")
    private String receiveNo;

    @Schema(description = "功能碼")
    @Column(name = "function_ind")
    private String functionInd;

    @Schema(description = "險種代碼")
    @Column(name = "plan_code")
    private String planCode;

    @Schema(description = "險種版數")
    @Column(name = "rate_scale")
    private String rateScale;

    @Schema(description = "保障狀態")
    @Column(name = "co_sts_code")
    private String coStsCode;

    @Schema(description = "保障生效日")
    @Column(name = "co_issue_date")
    private String coIssueDate;

    @Schema(description = "保額")
    @Column(name = "face_amt")
    private Double faceAmt;

    @Schema(description = "關係碼")
    @Column(name = "client_ident")
    private String clientIdent;

    public RscoEntity() {
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public Integer getCoverageNo() {
        return coverageNo;
    }

    public void setCoverageNo(Integer coverageNo) {
        this.coverageNo = coverageNo;
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

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getRateScale() {
        return rateScale;
    }

    public void setRateScale(String rateScale) {
        this.rateScale = rateScale;
    }

    public String getCoStsCode() {
        return coStsCode;
    }

    public void setCoStsCode(String coStsCode) {
        this.coStsCode = coStsCode;
    }

    public String getCoIssueDate() {
        return coIssueDate;
    }

    public void setCoIssueDate(String coIssueDate) {
        this.coIssueDate = coIssueDate;
    }

    public Double getFaceAmt() {
        return faceAmt;
    }

    public void setFaceAmt(Double faceAmt) {
        this.faceAmt = faceAmt;
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
        RscoEntity that = (RscoEntity) o;
        return Objects.equals(policyNo, that.policyNo) && Objects.equals(coverageNo, that.coverageNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNo, coverageNo);
    }
}
