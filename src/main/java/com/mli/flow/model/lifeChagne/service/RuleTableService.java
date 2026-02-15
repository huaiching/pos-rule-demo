package com.mli.flow.model.lifeChagne.service;

import com.mli.flow.model.common.exceptions.MliException;
import com.mli.flow.model.lifeChagne.dto.rule.PsecDTO;
import com.mli.flow.model.lifeChagne.dto.rule.RuleExpressionDTO;
import com.mli.flow.model.lifeChagne.dto.rule.RuleMessageDTO;
import com.mli.flow.model.lifeChagne.dto.rule.RuleTableDTO;
import com.mli.flow.model.lifeChagne.util.MliLoadExcelUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 核保訊息檔 及 規則表 相關邏輯
 */

@Service
public class RuleTableService {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 取得 核保訊息檔 及 規則表
     */
    public RuleTableDTO execute() {
        RuleTableDTO ruleTableDTO = new RuleTableDTO();
        ruleTableDTO.setPsecList(getPsec());
        ruleTableDTO.setRuleExpressionList(getRuleExpression());
        return ruleTableDTO;
    }

    /**
     * 取得 核保訊息檔
     */
    private List<PsecDTO> getPsec() {
        String sql = "select * from psec";
        List<PsecDTO> psecDTOList = namedParameterJdbcTemplate.query(sql, new HashMap<>(), new BeanPropertyRowMapper<>(PsecDTO.class));

        List<RuleMessageDTO> ruleMessageDTOList = getRuleMessage();
        for (PsecDTO psecDTO : psecDTOList) {
            RuleMessageDTO ruleMessageDTO = ruleMessageDTOList.stream()
                    .filter(rule -> rule.getNbErrCode().equals(psecDTO.getNbErrCode()))
                    .findFirst().orElse(null);

            psecDTO.setRuleMessageDTO(ruleMessageDTO);
        }

        return psecDTOList;
    }

    /**
     * 取得 檢核規則表
     */
    private List<RuleExpressionDTO> getRuleExpression() {
        List<RuleExpressionDTO> ruleExpressionList = new ArrayList<>();
        try {
            List<Object[]> loadDataList = MliLoadExcelUtil.loadExcelFromResources("templates/RuleExpression.xlsx", true);
            for (Object[] data : loadDataList) {
                RuleExpressionDTO ruleExpressionDTO = new RuleExpressionDTO();
                ruleExpressionDTO.setNbErrCode(String.valueOf(data[0]));
                ruleExpressionDTO.setGroupCode(String.valueOf(data[1]));
                ruleExpressionDTO.setRuleModel(String.valueOf(data[2]));
                ruleExpressionDTO.setExpression(String.valueOf(data[3]));
                ruleExpressionList.add(ruleExpressionDTO);
            }
        } catch (IOException | InvalidFormatException e) {
            throw new MliException(HttpStatus.INTERNAL_SERVER_ERROR, "規則表 Excel 讀取失敗:" + e.getMessage());
        }
        return ruleExpressionList;
    }

    private List<RuleMessageDTO> getRuleMessage() {
        List<RuleMessageDTO> ruleMessageDTOList = new ArrayList<>();
        try {
            List<Object[]> loadDataList = MliLoadExcelUtil.loadExcelFromResources("templates/RuleMessage.xlsx", true);
            for (Object[] data : loadDataList) {
                RuleMessageDTO ruleMessageDTO = new RuleMessageDTO();
                ruleMessageDTO.setNbErrCode(String.valueOf(data[0]));
                ruleMessageDTO.setMessageType(String.valueOf(data[1]));
                ruleMessageDTO.setMessageTemplate(String.valueOf(data[2]));
                ruleMessageDTOList.add(ruleMessageDTO);
            }
        } catch (IOException | InvalidFormatException e) {
            throw new MliException(HttpStatus.INTERNAL_SERVER_ERROR, "規則表 Excel 讀取失敗:" + e.getMessage());
        }
        return ruleMessageDTOList;
    }
}
