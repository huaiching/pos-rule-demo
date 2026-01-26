package com.mli.flow.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "polf")
@Schema(description = "壽險保單檔")
public class PolfEntity {
    @Id
    @Schema(description = "保單號碼")
    @Column(name = "policy_no")
    private String policyNo;

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

    public PolfEntity() {
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
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
        PolfEntity that = (PolfEntity) o;
        return Objects.equals(policyNo, that.policyNo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(policyNo);
    }
}
