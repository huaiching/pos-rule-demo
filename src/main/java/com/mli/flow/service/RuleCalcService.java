package com.mli.flow.service;

import com.mli.flow.contract.ChangeVariableContract;
import com.mli.flow.entity.*;
import com.mli.flow.repository.*;
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

    public Map<String, Object> execute() {
        // 設定 共用的檢核變數
        Map<String, Object> commonMap = setCommonModel();
        // 設定資料: 基本模組
        Map<String, Object> basicDataMap = setBasicModel(commonMap);
        // 設定資料: 保障模組
        List<Map<String, Object>> converageDataMapList = setCoverageModel(commonMap);
        // 設定資料: 客戶模組
        List<Map<String, Object>> clientDataMapList = setClientModel(commonMap);

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
     * @param commonMap 共用的檢核變數
     */
    private Map<String, Object> setBasicModel(Map<String, Object> commonMap) {
        // 取得 測試用初始資料
        CompletableFuture<PolfEntity> polfFuture = CompletableFuture.supplyAsync(() -> loadDataService.getPolf());
        CompletableFuture<RspoEntity> rspoFuture = CompletableFuture.supplyAsync(() -> loadDataService.getRslf());
        CompletableFuture<ChswEntity> chswFuture = CompletableFuture.supplyAsync(() -> loadDataService.getChsw());

        PolfEntity polf = polfFuture.join();
        RspoEntity rspo = rspoFuture.join();
        ChswEntity chsw = chswFuture.join();

        // 資料設定
        Map<String, Object> basicDataMap = new HashMap<>();
        basicDataMap.put("polf", polf);
        basicDataMap.put("rspo", rspo);
        basicDataMap.put("chsw", chsw);
        // 設定輸出
        basicDataMap.putAll(commonMap);

        return basicDataMap;
    }

    /**
     * 設定 保障模組 的 檢核變數
     *
     * @param commonMap 共用的檢核變數
     */
    private List<Map<String, Object>> setCoverageModel(Map<String, Object> commonMap) {
        // 取得 測試用初始資料
        CompletableFuture<List<ColfEntity>> colfFuture = CompletableFuture.supplyAsync(() -> loadDataService.getColf());
        CompletableFuture<List<RscoEntity>> rscoFuture = CompletableFuture.supplyAsync(() -> loadDataService.getRsco());
        CompletableFuture<List<PldfEntity>> pldfFuture = CompletableFuture.supplyAsync(() -> loadDataService.getPldf());

        List<ColfEntity> colfEntityList = colfFuture.join();
        List<RscoEntity> rscoEntityList = rscoFuture.join();
        List<PldfEntity> pldfEntityList = pldfFuture.join();

        // 資料設定
        List<Map<String, Object>> converageDataMapList = new ArrayList<>();
        for (RscoEntity rscoEntity : rscoEntityList) {
            Map<String, Object> converageDataMap = new HashMap<>();

            // rsco
            converageDataMap.put("rsco", rscoEntity);

            // colf
            ColfEntity colfEntity = colfEntityList.stream()
                    .filter(colf -> rscoEntity.getCoverageNo().equals(colf.getCoverageNo()))
                    .findFirst().orElse(null);
            converageDataMap.put("colf", colfEntity);

            // pldf: 變更後
            PldfEntity pldfEntityNew = pldfEntityList.stream()
                    .filter(pldf -> pldf.getPlanCode().equals(rscoEntity.getPlanCode()))
                    .filter(pldf -> pldf.getRateScale().equals(rscoEntity.getRateScale()))
                    .findFirst().orElse(null);
            converageDataMap.put("pldfNew", pldfEntityNew);

            // pldf: 變更前
            if (colfEntity != null) {
                PldfEntity pldfEntityOri = pldfEntityList.stream()
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
     * @param commonMap 共用的檢核變數
     */
    private List<Map<String, Object>> setClientModel(Map<String, Object> commonMap) {
        // 取得 測試用初始資料
        CompletableFuture<List<ClntEntity>> clntFuture = CompletableFuture.supplyAsync(() -> loadDataService.getClnt());
        CompletableFuture<List<AddrEntity>> addrFuture = CompletableFuture.supplyAsync(() -> loadDataService.getAddr());
        CompletableFuture<List<PoclEntity>> poclFuture = CompletableFuture.supplyAsync(() -> loadDataService.getPocl());
        CompletableFuture<List<RsclEntity>> rsclFuture = CompletableFuture.supplyAsync(() -> loadDataService.getRscl());
        CompletableFuture<List<RsdrEntity>> rsdrFuture = CompletableFuture.supplyAsync(() -> loadDataService.getRsdr());

        List<ClntEntity> clntEntityList = clntFuture.join();
        List<AddrEntity> addrEntityList = addrFuture.join();
        List<PoclEntity> poclEntityList = poclFuture.join();
        List<RsclEntity> rsclEntityList = rsclFuture.join();
        List<RsdrEntity> rsdrEntityList = rsdrFuture.join();

        // 資料設定
        List<Map<String, Object>> clientDataMapList = new ArrayList<>();
        rsclEntityList.stream().map(RsclEntity::getClientIdent).forEach(clientIdent -> {
            Map<String, Object> clientDataMap = new HashMap<>();

            // rscl
            RsclEntity rsclEntity = rsclEntityList.stream()
                    .filter(rscl -> rscl.getClientIdent().equals(clientIdent))
                    .findFirst().orElse(null);
            clientDataMap.put("rscl", rsclEntity);
            // rsdr
            List<RsdrEntity> rsdrEntities = rsdrEntityList.stream()
                    .filter(rsdr -> rsdr.getClientId().equals(rsclEntity.getClientId()))
                    .collect(Collectors.toList());
            clientDataMap.put("rsdr", rsdrEntities);

            // clnt & addr
            String clientIdOri = poclEntityList.stream()
                    .filter(pocl -> pocl.getClientIdent().equals(clientIdent))
                    .map(PoclEntity::getClientId)
                    .findFirst().orElse(null);
            if (!StringUtils.isBlank(clientIdOri)) {
                ClntEntity clntEntity = clntEntityList.stream()
                        .filter(clnt -> clnt.getClientId().equals(clientIdOri))
                        .findFirst().orElse(null);
                clientDataMap.put("clnt", clntEntity);
                List<AddrEntity> addrEntities = addrEntityList.stream()
                        .filter(addr -> addr.getClientId().equals(clientIdOri))
                        .collect(Collectors.toList());
                clientDataMap.put("addr", addrEntities);
            }
            // 設定輸出
            clientDataMap.putAll(commonMap);
            clientDataMapList.add(clientDataMap);
        });

        return clientDataMapList;
    }
}
