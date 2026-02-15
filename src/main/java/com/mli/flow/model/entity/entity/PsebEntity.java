package com.mli.flow.model.entity.entity;

import com.mli.flow.model.entity.uniquekey.PsebKey;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pseb")
@IdClass(PsebKey.class)
@Schema(description = "特殊代碼檔")
public class PsebEntity {
    @Id
    @Schema(description = "類型")
    @Column(name = "pseb_type")
    private String psebType;

    @Id
    @Schema(description = "代碼1")
    @Column(name = "pseb_code1")
    private String psebCode1;

    @Id
    @Schema(description = "代碼2")
    @Column(name = "pseb_code2")
    private String psebCode2;

    @Id
    @Schema(description = "字串資料1")
    @Column(name = "pseb_desc1")
    private String psebDesc1;

    @Id
    @Schema(description = "字串資料2")
    @Column(name = "pseb_desc2")
    private String psebDesc2;

    @Id
    @Schema(description = "字串資料3")
    @Column(name = "pseb_desc3")
    private String psebDesc3;

    @Id
    @Schema(description = "字串資料4")
    @Column(name = "pseb_desc4")
    private String psebDesc4;

    @Id
    @Schema(description = "字串資料5")
    @Column(name = "pseb_desc5")
    private String psebDesc5;

    public PsebEntity() {
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
        PsebEntity that = (PsebEntity) o;
        return Objects.equals(psebType, that.psebType) && Objects.equals(psebCode1, that.psebCode1) && Objects.equals(psebCode2, that.psebCode2) && Objects.equals(psebDesc1, that.psebDesc1) && Objects.equals(psebDesc2, that.psebDesc2) && Objects.equals(psebDesc3, that.psebDesc3) && Objects.equals(psebDesc4, that.psebDesc4) && Objects.equals(psebDesc5, that.psebDesc5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(psebType, psebCode1, psebCode2, psebDesc1, psebDesc2, psebDesc3, psebDesc4, psebDesc5);
    }
}
