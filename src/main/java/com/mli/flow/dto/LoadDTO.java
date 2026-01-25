package com.mli.flow.dto;

import com.mli.flow.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "DB讀取的前置資料")
public class LoadDTO {
    private PolfEntity polf;
    private List<ColfEntity> colfList;
    private List<PldfEntity> pldfOriList;
    private RspoEntity rspo;
    private List<RscoEntity> rscoList;
    private List<PldfEntity> pldfNewList;
    private ChswEntity chsw;
    private List<ClntEntity> clntList;
    private List<AddrEntity> addrList;
    private List<PoclEntity> poclList;
    private List<RsclEntity> rsclList;
    private List<RsdrEntity> rsdrList;

    public PolfEntity getPolf() {
        return polf;
    }

    public void setPolf(PolfEntity polf) {
        this.polf = polf;
    }

    public List<ColfEntity> getColfList() {
        return colfList;
    }

    public void setColfList(List<ColfEntity> colfList) {
        this.colfList = colfList;
    }

    public List<PldfEntity> getPldfOriList() {
        return pldfOriList;
    }

    public void setPldfOriList(List<PldfEntity> pldfOriList) {
        this.pldfOriList = pldfOriList;
    }

    public RspoEntity getRspo() {
        return rspo;
    }

    public void setRspo(RspoEntity rspo) {
        this.rspo = rspo;
    }

    public List<RscoEntity> getRscoList() {
        return rscoList;
    }

    public void setRscoList(List<RscoEntity> rscoList) {
        this.rscoList = rscoList;
    }

    public List<PldfEntity> getPldfNewList() {
        return pldfNewList;
    }

    public void setPldfNewList(List<PldfEntity> pldfNewList) {
        this.pldfNewList = pldfNewList;
    }

    public ChswEntity getChsw() {
        return chsw;
    }

    public void setChsw(ChswEntity chsw) {
        this.chsw = chsw;
    }

    public List<ClntEntity> getClntList() {
        return clntList;
    }

    public void setClntList(List<ClntEntity> clntList) {
        this.clntList = clntList;
    }

    public List<AddrEntity> getAddrList() {
        return addrList;
    }

    public void setAddrList(List<AddrEntity> addrList) {
        this.addrList = addrList;
    }

    public List<PoclEntity> getPoclList() {
        return poclList;
    }

    public void setPoclList(List<PoclEntity> poclList) {
        this.poclList = poclList;
    }

    public List<RsclEntity> getRsclList() {
        return rsclList;
    }

    public void setRsclList(List<RsclEntity> rsclList) {
        this.rsclList = rsclList;
    }

    public List<RsdrEntity> getRsdrList() {
        return rsdrList;
    }

    public void setRsdrList(List<RsdrEntity> rsdrList) {
        this.rsdrList = rsdrList;
    }
}
