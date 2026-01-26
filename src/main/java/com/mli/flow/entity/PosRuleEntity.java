
package com.mli.flow.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pos_rule")
@Schema(description = "核保規則表")
public class PosRuleEntity {
    @Id
    @Schema(description = "流水號")
    @Column(name = "id")
    private Long id;

    @Schema(description = "核保訊息代碼")
    @Column(name = "nb_err_code")
    private String nbErrCode;

    @Schema(description = "檢核群組編號")
    @Column(name = "group_code")
    private String groupCode;

    @Schema(description = "規則模型")
    @Column(name = "rule_model")
    private String ruleModel;

    @Schema(description = "檢核規則")
    @Column(name = "expression")
    private String expression;

    @Schema(description = "效性：Y=啟用, N=停用")
    @Column(name = "active_flag")
    private String activeFlag;

    public PosRuleEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNbErrCode() {
        return nbErrCode;
    }

    public void setNbErrCode(String nbErrCode) {
        this.nbErrCode = nbErrCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getRuleModel() {
        return ruleModel;
    }

    public void setRuleModel(String ruleModel) {
        this.ruleModel = ruleModel;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PosRuleEntity that = (PosRuleEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
