package com.mli.flow.service;

import com.mli.flow.dto.CheckResultDTO;
import com.mli.flow.service.engine.CheckService;
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
    private RuleCalcService ruleCalcService;
    @Autowired
    private CheckService checkService;

    public List<CheckResultDTO> execute() {
        /** 變數設定 **/
        Map<String, Object> dataMap = ruleCalcService.execute();

        /** 規則檢核 **/
        List<CheckResultDTO> result = checkService.execute(dataMap);

        return result;
    }
}
