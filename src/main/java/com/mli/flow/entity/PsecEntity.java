
package com.mli.flow.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "psec")
@Schema(description = "核保訊息表")
public class PsecEntity {
    @Id
    @Schema(description = "核保訊息代碼")
    @Column(name = "nb_err_code")
    private String nbErrCode;

    @Schema(description = "核保訊息文字")
    @Column(name = "nb_err_desc")
    private String nbErrDesc;

    @Schema(description = "核保等級")
    @Column(name = "severity")
    private String severity;

    public PsecEntity() {
    }

    public String getNbErrCode() {
        return nbErrCode;
    }

    public void setNbErrCode(String nbErrCode) {
        this.nbErrCode = nbErrCode;
    }

    public String getNbErrDesc() {
        return nbErrDesc;
    }

    public void setNbErrDesc(String nbErrDesc) {
        this.nbErrDesc = nbErrDesc;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PsecEntity that = (PsecEntity) o;
        return Objects.equals(nbErrCode, that.nbErrCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nbErrCode);
    }
}
