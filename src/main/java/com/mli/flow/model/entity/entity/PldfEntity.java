package com.mli.flow.model.entity.entity;

import com.mli.flow.model.entity.uniquekey.PldfKey;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pldf")
@IdClass(PldfKey.class)
@Schema(description = "商品檔")
public class PldfEntity {
    @Id
    @Schema(description = "險種代碼")
    @Column(name = "plan_code")
    private String planCode;

    @Id
    @Schema(description = "險種版數")
    @Column(name = "rate_scale")
    private String rateScale;

    @Schema(description = "險種簡稱")
    @Column(name = "plan_abbr_code")
    private String planAbbrCode;

    @Schema(description = "險種類別代碼")
    @Column(name = "plan_class_code")
    private String planClassCode;

    public PldfEntity() {
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

    public String getPlanAbbrCode() {
        return planAbbrCode;
    }

    public void setPlanAbbrCode(String planAbbrCode) {
        this.planAbbrCode = planAbbrCode;
    }

    public String getPlanClassCode() {
        return planClassCode;
    }

    public void setPlanClassCode(String planClassCode) {
        this.planClassCode = planClassCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PldfEntity that = (PldfEntity) o;
        return Objects.equals(planCode, that.planCode) && Objects.equals(rateScale, that.rateScale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planCode, rateScale);
    }
}
