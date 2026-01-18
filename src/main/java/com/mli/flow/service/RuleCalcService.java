package com.mli.flow.service;

import com.mli.flow.entity.*;
import com.mli.flow.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Map<String, Object> execute() {
        // 取得 測試用初始資料
        List<PolfEntity> polfEntityList = polfRepository.findAll();
        List<ColfEntity> colfEntityList = colfRepository.findAll();
        List<ClntEntity> clntEntityList = clntRepository.findAll();
        List<AddrEntity> addrEntityList = addrRepository.findAll();
        List<PoclEntity> poclEntityList = poclRepository.findAll();
        List<RspoEntity> rspoEntityList = rspoRepository.findAll();
        List<RscoEntity> rscoEntityList = rscoRepository.findAll();
        List<RsclEntity> rsclEntityList = rsclRepository.findAll();
        List<RsdrEntity> rsdrEntityList = rsdrRepository.findAll();
        List<ChswEntity> chswEntityList = chswRepository.findAll();

        // 設定資料: 基本模組
        Map<String, Object> basicDataMap = new HashMap<>();
        basicDataMap.put("polf", polfEntityList.get(0));
        basicDataMap.put("rspo", rspoEntityList.get(0));
        basicDataMap.put("chsw", chswEntityList.get(0));
        // 設定資料: 保障模組
        List<Map<String, Object>> converageDataMapList = new ArrayList<>();
        for (RscoEntity rscoEntity : rscoEntityList) {
            Map<String, Object> converageDataMap = new HashMap<>();
            converageDataMap.put("rsco", rscoEntity);

            ColfEntity colfEntity = colfEntityList.stream()
                    .filter(colf -> rscoEntity.getCoverageNo().equals(colf.getCoverageNo()))
                    .findFirst().orElse(null);
            converageDataMap.put("colf", colfEntity);
            converageDataMapList.add(converageDataMap);
        }
        // 設定資料: 客戶模組
        List<Map<String, Object>> clientDataMapList = new ArrayList<>();
        rsclEntityList.stream().map(RsclEntity::getClientIdent).forEach(clientIdent -> {
            Map<String, Object> clientDataMap = new HashMap<>();
            RsclEntity rsclEntity = rsclEntityList.stream()
                    .filter(rscl -> rscl.getClientIdent().equals(clientIdent))
                    .findFirst().orElse(null);
            clientDataMap.put("rscl", rsclEntity);
            List<RsdrEntity> rsdrEntities = rsdrEntityList.stream()
                    .filter(rsdr -> rsdr.getClientId().equals(rsclEntity.getClientId()))
                    .collect(Collectors.toList());
            clientDataMap.put("rsdr", rsdrEntities);

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
            clientDataMapList.add(clientDataMap);
        });

        // 設定輸出
        Map<String, Object> outputMap = new HashMap<>();
        outputMap.put("basic", basicDataMap);
        outputMap.put("coverage", converageDataMapList);
        outputMap.put("client", clientDataMapList);

        return outputMap;
    }
}
