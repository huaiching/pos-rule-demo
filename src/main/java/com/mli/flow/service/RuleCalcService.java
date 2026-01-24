package com.mli.flow.service;

import com.mli.flow.contract.ChangeVariableContract;
import com.mli.flow.entity.*;
import com.mli.flow.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 核保檢核 檢核變數整理
 */
@Service
public class RuleCalcService {
    @Autowired
    private PolfRepository polfRepository;
    @Autowired
    private ColfRepository colfRepository;
    @Autowired
    private ClntRepository clntRepository;
    @Autowired
    private AddrRepository addrRepository;
    @Autowired
    private PoclRepository poclRepository;
    @Autowired
    private RspoRepository rspoRepository;
    @Autowired
    private RscoRepository rscoRepository;
    @Autowired
    private RsclRepository rsclRepository;
    @Autowired
    private RsdrRepository rsdrRepository;
    @Autowired
    private ChswRepository chswRepository;
    @Autowired
    private PldfRepository pldfRepository;
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
        List<PolfEntity> polfEntityList = polfRepository.findAll();
        List<RspoEntity> rspoEntityList = rspoRepository.findAll();
        List<ChswEntity> chswEntityList = chswRepository.findAll();

        // 資料設定
        Map<String, Object> basicDataMap = new HashMap<>();
        basicDataMap.put("polf", polfEntityList.get(0));
        basicDataMap.put("rspo", rspoEntityList.get(0));
        basicDataMap.put("chsw", chswEntityList.get(0));
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
        List<ColfEntity> colfEntityList = colfRepository.findAll();
        List<RscoEntity> rscoEntityList = rscoRepository.findAll();
        List<PldfEntity> pldfEntityList = pldfRepository.findAll();

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
        List<ClntEntity> clntEntityList = clntRepository.findAll();
        List<AddrEntity> addrEntityList = addrRepository.findAll();
        List<PoclEntity> poclEntityList = poclRepository.findAll();
        List<RsclEntity> rsclEntityList = rsclRepository.findAll();
        List<RsdrEntity> rsdrEntityList = rsdrRepository.findAll();

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
            if (!StringUtils.isEmpty(clientIdOri)) {
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
