package com.mli.flow.model.lifeChagne.dto.spel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

@Schema(description = "LIFE 契變 的 SpEL 檢核變數")
public class RuleDataDTO {
    @Schema(description = "基本模組 檢核變數")
    private Map<String, Object> basicDataMap;
    @Schema(description = "保障模組 檢核變數")
    private List<Map<String, Object>> converageDataMapList;
    @Schema(description = "客戶模組 檢核變數")
    private List<Map<String, Object>> clientDataMapList;

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
}
