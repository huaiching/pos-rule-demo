package com.mli.flow.dto;

import com.mli.flow.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "變更資料")
public class ChangeNewDataDTO {
    private RspoEntity rspo;
    private List<RscoEntity> rsco;
    private List<RsclEntity> rscl;
    private List<RsdrEntity> rsdr;
    private List<PoclEntity> pocl;

    public RspoEntity getRspo() {
        return rspo;
    }

    public void setRspo(RspoEntity rspo) {
        this.rspo = rspo;
    }

    public List<RscoEntity> getRsco() {
        return rsco;
    }

    public void setRsco(List<RscoEntity> rsco) {
        this.rsco = rsco;
    }

    public List<RsclEntity> getRscl() {
        return rscl;
    }

    public void setRscl(List<RsclEntity> rscl) {
        this.rscl = rscl;
    }

    public List<RsdrEntity> getRsdr() {
        return rsdr;
    }

    public void setRsdr(List<RsdrEntity> rsdr) {
        this.rsdr = rsdr;
    }

    public List<PoclEntity> getPocl() {
        return pocl;
    }

    public void setPocl(List<PoclEntity> pocl) {
        this.pocl = pocl;
    }
}
