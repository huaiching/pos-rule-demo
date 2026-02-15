package com.mli.flow.model.lifeChagne.service;

import com.mli.flow.model.entity.entity.*;
import com.mli.flow.model.lifeChagne.contract.ChangeVariableContract;
import com.mli.flow.model.lifeChagne.dto.LoadDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 核保檢核 檢核變數整理
 */
@Service
public class RuleCalcService {
    @Autowired
    private LoadDataService loadDataService;
    @Autowired
    private List<ChangeVariableContract> changeVariableContractList;

    /**
     * 檢核變數設定
     *
     * @param loadDTO 初始資料
     */
    public Map<String, Object> execute(LoadDTO loadDTO) {
        // 設定 共用的檢核變數
        Map<String, Object> commonMap = setCommonModel();
        // 設定資料: 基本模組
        Map<String, Object> basicDataMap = setBasicModel(loadDTO, commonMap);
        // 設定資料: 保障模組
        List<Map<String, Object>> converageDataMapList = setCoverageModel(loadDTO, commonMap);
        // 設定資料: 客戶模組
        List<Map<String, Object>> clientDataMapList = setClientModel(loadDTO, commonMap);

        // 設定輸出
        Map<String, Object> outputMap = new HashMap<>();
        outputMap.put("basic", basicDataMap);
        outputMap.put("coverage", converageDataMapList);
        outputMap.put("client", clientDataMapList);

        return outputMap;
    }

    /**
     * 設定 共用的檢核變數 的 檢核變數
     */
    private Map<String, Object> setCommonModel() {
        Map<String, Object> commonMap = new HashMap<>();
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        for (ChangeVariableContract changeVariableContract : changeVariableContractList) {
            futureList.add(CompletableFuture.runAsync(() -> changeVariableContract.execute(commonMap)));
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();

        return commonMap;
    }

    /**
     * 設定 基本模組 的 檢核變數
     *
     * @param loadDTO   初始資料
     * @param commonMap 共用的檢核變數
     */
    private Map<String, Object> setBasicModel(LoadDTO loadDTO, Map<String, Object> commonMap) {
        // 資料設定
        Map<String, Object> basicDataMap = new HashMap<>();
        basicDataMap.put("polf", loadDTO.getPolf());
        basicDataMap.put("rspo", loadDTO.getRspo());
        basicDataMap.put("chsw", loadDTO.getChsw());
        // 設定輸出
        basicDataMap.putAll(commonMap);

        return basicDataMap;
    }

    /**
     * 設定 保障模組 的 檢核變數
     *
     * @param loadDTO   初始資料
     * @param commonMap 共用的檢核變數
     */
    private List<Map<String, Object>> setCoverageModel(LoadDTO loadDTO, Map<String, Object> commonMap) {

        List<ColfEntity> colfList = loadDTO.getColfList();
        List<RscoEntity> rscoList = loadDTO.getRscoList();
        List<PldfEntity> pldfOriList = loadDTO.getPldfOriList();
        List<PldfEntity> pldfNewList = loadDTO.getPldfNewList();

        // 資料設定
        List<Map<String, Object>> converageDataMapList = new ArrayList<>();
        for (RscoEntity rscoEntity : rscoList) {
            Map<String, Object> converageDataMap = new HashMap<>();
            // 變更後
            /* rsco */
            converageDataMap.put("rsco", rscoEntity);
            /* pldf */
            PldfEntity pldfNewEntity = pldfNewList.stream()
                    .filter(pldfEntity -> pldfEntity.getPlanCode().equals(rscoEntity.getPlanCode()))
                    .filter(pldfEntity -> pldfEntity.getRateScale().equals(rscoEntity.getRateScale()))
                    .findFirst().orElse(null);
            converageDataMap.put("pldfNew", pldfNewEntity);

            // 變更前
            ColfEntity colfEntity = colfList.stream()
                    .filter(colf -> rscoEntity.getCoverageNo().equals(colf.getCoverageNo()))
                    .findFirst().orElse(null);
            if (colfEntity != null) {
                /* colf */
                converageDataMap.put("colf", colfEntity);
                /* pldf */
                PldfEntity pldfEntityOri = pldfOriList.stream()
                        .filter(pldf -> pldf.getPlanCode().equals(colfEntity.getPlanCode()))
                        .filter(pldf -> pldf.getRateScale().equals(colfEntity.getRateScale()))
                        .findFirst().orElse(null);
                converageDataMap.put("pldfOri", pldfEntityOri);
            }

            // 設定輸出
            converageDataMap.putAll(commonMap);
            converageDataMapList.add(converageDataMap);
        }

        return converageDataMapList;
    }

    /**
     * 設定 客戶模組 的 檢核變數
     *
     * @param loadDTO   初始資料
     * @param commonMap 共用的檢核變數
     */
    private List<Map<String, Object>> setClientModel(LoadDTO loadDTO, Map<String, Object> commonMap) {
        List<Map<String, Object>> clientDataMapList = new ArrayList<>();
        for (RsclEntity rsclEntity : loadDTO.getRsclList()) {
            Map<String, Object> clientDataMap = new HashMap<>();
            // 變更後
            /* rscl */
            clientDataMap.put("rscl", rsclEntity);
            /* rsdr */
            List<RsdrEntity> rsdrEntityList = loadDTO.getRsdrList().stream()
                    .filter(rsdrEntity -> rsdrEntity.getClientId().equals(rsclEntity.getClientId()))
                    .toList();
            clientDataMap.put("rsdr", rsdrEntityList);

            // 變更前
            String clientIdent = rsclEntity.getClientIdent();
            String clientIdOri = loadDTO.getPoclList().stream()
                    .filter(pocl -> pocl.getClientIdent().equals(clientIdent))
                    .map(PoclEntity::getClientId)
                    .findFirst().orElse(null);
            if (StringUtils.isBlank(clientIdent)) {
                /* clnt */
                ClntEntity clntEntity = loadDTO.getClntList().stream()
                        .filter(clnt -> clnt.getClientId().equals(clientIdOri))
                        .findFirst().orElse(null);
                clientDataMap.put("clnt", clntEntity);
                /* addr */
                List<AddrEntity> addrEntities = loadDTO.getAddrList().stream()
                        .filter(addr -> addr.getClientId().equals(clientIdOri))
                        .collect(Collectors.toList());
                clientDataMap.put("addr", addrEntities);
            }
            // 設定輸出
            clientDataMap.putAll(commonMap);
            clientDataMapList.add(clientDataMap);
        }

        return clientDataMapList;
    }
}
