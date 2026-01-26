package com.mli.flow.uniquekey;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PsebKey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "類型")
    private String psebType;

    @Schema(description = "代碼1")
    private String psebCode1;

    @Schema(description = "代碼2")
    private String psebCode2;

    @Schema(description = "字串資料1")
    private String psebDesc1;

    @Schema(description = "字串資料2")
    private String psebDesc2;

    @Schema(description = "字串資料3")
    private String psebDesc3;

    @Schema(description = "字串資料4")
    private String psebDesc4;

    @Schema(description = "字串資料5")
    private String psebDesc5;

    public PsebKey() {
    }

    public String getPsebType() {
        return psebType;
    }

    public void setPsebType(String psebType) {
        this.psebType = psebType;
    }

    public String getPsebCode1() {
        return psebCode1;
    }

    public void setPsebCode1(String psebCode1) {
        this.psebCode1 = psebCode1;
    }

    public String getPsebCode2() {
        return psebCode2;
    }

    public void setPsebCode2(String psebCode2) {
        this.psebCode2 = psebCode2;
    }

    public String getPsebDesc1() {
        return psebDesc1;
    }

    public void setPsebDesc1(String psebDesc1) {
        this.psebDesc1 = psebDesc1;
    }

    public String getPsebDesc2() {
        return psebDesc2;
    }

    public void setPsebDesc2(String psebDesc2) {
        this.psebDesc2 = psebDesc2;
    }

    public String getPsebDesc3() {
        return psebDesc3;
    }

    public void setPsebDesc3(String psebDesc3) {
        this.psebDesc3 = psebDesc3;
    }

    public String getPsebDesc4() {
        return psebDesc4;
    }

    public void setPsebDesc4(String psebDesc4) {
        this.psebDesc4 = psebDesc4;
    }

    public String getPsebDesc5() {
        return psebDesc5;
    }

    public void setPsebDesc5(String psebDesc5) {
        this.psebDesc5 = psebDesc5;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PsebKey psebKey = (PsebKey) o;
        return Objects.equals(psebType, psebKey.psebType) && Objects.equals(psebCode1, psebKey.psebCode1) && Objects.equals(psebCode2, psebKey.psebCode2) && Objects.equals(psebDesc1, psebKey.psebDesc1) && Objects.equals(psebDesc2, psebKey.psebDesc2) && Objects.equals(psebDesc3, psebKey.psebDesc3) && Objects.equals(psebDesc4, psebKey.psebDesc4) && Objects.equals(psebDesc5, psebKey.psebDesc5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(psebType, psebCode1, psebCode2, psebDesc1, psebDesc2, psebDesc3, psebDesc4, psebDesc5);
    }
}
