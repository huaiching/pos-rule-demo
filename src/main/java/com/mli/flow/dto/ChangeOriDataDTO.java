package com.mli.flow.dto;

import com.mli.flow.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "變更資料")
public class ChangeOriDataDTO {
    private PolfEntity polf;
    private List<ColfEntity> colf;
    private List<ClntEntity> clnt;
    private List<AddrEntity> addr;
    private List<PoclEntity> pocl;

    public PolfEntity getPolf() {
        return polf;
    }

    public void setPolf(PolfEntity polf) {
        this.polf = polf;
    }

    public List<ColfEntity> getColf() {
        return colf;
    }

    public void setColf(List<ColfEntity> colf) {
        this.colf = colf;
    }

    public List<ClntEntity> getClnt() {
        return clnt;
    }

    public void setClnt(List<ClntEntity> clnt) {
        this.clnt = clnt;
    }

    public List<AddrEntity> getAddr() {
        return addr;
    }

    public void setAddr(List<AddrEntity> addr) {
        this.addr = addr;
    }

    public List<PoclEntity> getPocl() {
        return pocl;
    }

    public void setPocl(List<PoclEntity> pocl) {
        this.pocl = pocl;
    }
}
