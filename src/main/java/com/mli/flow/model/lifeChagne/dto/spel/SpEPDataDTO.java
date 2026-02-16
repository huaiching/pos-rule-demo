package com.mli.flow.model.lifeChagne.dto.spel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

@Schema(description = "LIFE 契變 的 SpEL 檢核變數")
public class SpEPDataDTO {
    @Schema(description = "基本模組 檢核變數")
    private Map<String, Object> basicDataMap;
    @Schema(description = "保障模組 檢核變數")
    private List<Map<String, Object>> converageDataMapList;
    @Schema(description = "客戶模組 檢核變數")
    private List<Map<String, Object>> clientDataMapList;
    @Schema(description = "受益人模組 檢核變數")
    private List<Map<String, Object>> beneftDataMapList;
    @Schema(description = "投資模組 檢核變數")
    private List<Map<String, Object>> investDataMapList;
    @Schema(description = "累計保額模組 檢核變數")
    private List<Map<String, Object>> totalFaceAmtDataMapList;

    public Map<String, Object> getBasicDataMap() {
        return basicDataMap;
    }

    public void setBasicDataMap(Map<String, Object> basicDataMap) {
        this.basicDataMap = basicDataMap;
    }

    public List<Map<String, Object>> getConverageDataMapList() {
        return converageDataMapList;
    }

    public void setConverageDataMapList(List<Map<String, Object>> converageDataMapList) {
        this.converageDataMapList = converageDataMapList;
    }

    public List<Map<String, Object>> getClientDataMapList() {
        return clientDataMapList;
    }

    public void setClientDataMapList(List<Map<String, Object>> clientDataMapList) {
        this.clientDataMapList = clientDataMapList;
    }

    public List<Map<String, Object>> getBeneftDataMapList() {
        return beneftDataMapList;
    }

    public void setBeneftDataMapList(List<Map<String, Object>> beneftDataMapList) {
        this.beneftDataMapList = beneftDataMapList;
    }

    public List<Map<String, Object>> getInvestDataMapList() {
        return investDataMapList;
    }

    public void setInvestDataMapList(List<Map<String, Object>> investDataMapList) {
        this.investDataMapList = investDataMapList;
    }

    public List<Map<String, Object>> getTotalFaceAmtDataMapList() {
        return totalFaceAmtDataMapList;
    }

    public void setTotalFaceAmtDataMapList(List<Map<String, Object>> totalFaceAmtDataMapList) {
        this.totalFaceAmtDataMapList = totalFaceAmtDataMapList;
    }
}
