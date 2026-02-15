package com.mli.flow.model.lifeChagne.service;

import com.mli.flow.model.lifeChagne.dto.LoadDTO;
import com.mli.flow.model.lifeChagne.dto.engine.CheckResultDTO;
import com.mli.flow.model.lifeChagne.dto.rule.RuleTableDTO;
import com.mli.flow.model.lifeChagne.service.engine.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 核保檢核 主流程
 */
@Service
public class RuleMainService {
    @Autowired
    private LoadDataService loadDataService;
    @Autowired
    private RuleCalcService ruleCalcService;
    @Autowired
    private CheckService checkService;
    @Autowired
    private RuleTableService ruleTableService;

    public List<CheckResultDTO> execute(String policyNo, String receiveNo) {
        /** 初始資料讀取 **/
        LoadDTO loadDTO = loadDataService.getAllData(policyNo, receiveNo);

        /** 變數設定 **/
        Map<String, Object> dataMap = ruleCalcService.execute(loadDTO);

        /** 核保訊息檔 + 規則表 **/
        RuleTableDTO ruleTableDTO = ruleTableService.execute();

        /** 規則檢核 **/
        List<CheckResultDTO> result = checkService.execute(ruleTableDTO, dataMap);

        return result;
    }
}
