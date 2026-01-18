package com.mli.flow.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "rspo")
@Schema(description = "壽險保單暫存檔")
public class RspoEntity {
    @Id
    @Schema(description = "保單號碼")
    @Column(name = "policy_no")
    private String policyNo;

    @Schema(description = "受理號碼")
    @Column(name = "receive_no")
    private String receiveNo;

    @Schema(description = "保單狀態")
    @Column(name = "po_sts_code")
    private String poStsCode;

    @Schema(description = "保單生效日")
    @Column(name = "po_issue_date")
    private String poIssueDate;

    @Schema(description = "幣別")
    @Column(name = "currency")
    private String currency;

    @Schema(description = "主約險種代碼")
    @Column(name = "basic_plan_code")
    private String basicPlanCode;

    @Schema(description = "主約險種版數")
    @Column(name = "basic_rate_scale")
    private String basicRateScale;

    public RspoEntity() {
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

    public String getPoStsCode() {
        return poStsCode;
    }

    public void setPoStsCode(String poStsCode) {
        this.poStsCode = poStsCode;
    }

    public String getPoIssueDate() {
        return poIssueDate;
    }

    public void setPoIssueDate(String poIssueDate) {
        this.poIssueDate = poIssueDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBasicPlanCode() {
        return basicPlanCode;
    }

    public void setBasicPlanCode(String basicPlanCode) {
        this.basicPlanCode = basicPlanCode;
    }

    public String getBasicRateScale() {
        return basicRateScale;
    }

    public void setBasicRateScale(String basicRateScale) {
        this.basicRateScale = basicRateScale;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RspoEntity that = (RspoEntity) o;
        return Objects.equals(policyNo, that.policyNo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(policyNo);
    }
}
