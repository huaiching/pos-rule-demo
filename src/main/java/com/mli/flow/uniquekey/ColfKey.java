package com.mli.flow.uniquekey;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ColfKey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "保單號碼")
    private String policyNo;

    @Schema(description = "保障序號")
    private Integer coverageNo;

    public ColfKey() {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ColfKey colfKey = (ColfKey) o;
        return Objects.equals(policyNo, colfKey.policyNo) && Objects.equals(coverageNo, colfKey.coverageNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNo, coverageNo);
    }
}
