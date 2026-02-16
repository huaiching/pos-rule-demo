package com.mli.flow.model.lifeChagne.service.engine;

import com.mli.flow.model.lifeChagne.constants.MessageTypeEnum;
import com.mli.flow.model.lifeChagne.constants.RuleTypeEnum;
import com.mli.flow.model.lifeChagne.dto.engine.CheckResultDTO;
import com.mli.flow.model.lifeChagne.dto.engine.RuleEvalResultDTO;
import com.mli.flow.model.lifeChagne.dto.rule.PsecDTO;
import com.mli.flow.model.lifeChagne.dto.rule.RuleExpressionDTO;
import com.mli.flow.model.lifeChagne.dto.rule.RuleMessageDTO;
import com.mli.flow.model.lifeChagne.dto.rule.RuleTableDTO;
import com.mli.flow.model.lifeChagne.dto.spel.SpEPDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * 核保訊息 規則檢核: 主流程
 */
@Service
public class CheckService {
    @Autowired
    private SpelRuleService spelRuleService;

    /**
     * 執行核保訊息檢核
     *
     * @param ruleTableDTO 檢核規則相關檔案
     * @param spEPDataDTO  檢核變數
     */
    public List<CheckResultDTO> execute(RuleTableDTO ruleTableDTO, SpEPDataDTO spEPDataDTO) {
        List<CheckResultDTO> results = new CopyOnWriteArrayList<>();
        // 取得所有核保規則
        List<PsecDTO> psecList = ruleTableDTO.getPsecList();
        // 取得所有檢核規則
        List<RuleExpressionDTO> ruleExpressionList = ruleTableDTO.getRuleExpressionList();

        // 進行核保檢核
        psecList.parallelStream().forEach(psec -> {
            results.addAll(Objects.requireNonNull(evaluateRule(psec, ruleExpressionList, spEPDataDTO)));
        });

        return results;
    }

    /**
     * 單一核保訊息檢核
     *
     * @param psecEntity         核保訊息表
     * @param ruleExpressionList 核保規則表
     * @param spEPDataDTO        檢核變數
     */
    private List<CheckResultDTO> evaluateRule(PsecDTO psecEntity, List<RuleExpressionDTO> ruleExpressionList, SpEPDataDTO spEPDataDTO) {
        List<CheckResultDTO> checkResultDTOList = new CopyOnWriteArrayList<>();
        // 取得 要檢核的規則
        List<RuleExpressionDTO> checkRuleList = ruleExpressionList.stream()
                .filter(rule -> rule.getNbErrCode().equals(psecEntity.getNbErrCode()))
                .toList();
        if (checkRuleList.isEmpty()) {
            return null;
        }

        // 取得所有群組 (OR 關係)
        List<String> groupCodeList = checkRuleList.stream()
                .map(RuleExpressionDTO::getGroupCode).distinct()
                .toList();

        // 依照群組進行檢核：任一群組成立即成立
        groupCodeList.parallelStream().forEach(groupCode -> {
            // 取得 該群組的 檢核規則
            List<RuleExpressionDTO> groupRules = checkRuleList.stream()
                    .filter(rule -> rule.getGroupCode().equals(groupCode))
                    .collect(Collectors.toList());
            // 執行檢核
            RuleEvalResultDTO evalResult = evaluateGroup(groupRules, spEPDataDTO);

            if (evalResult.getMatched()) {
                // 群組命中，生成結果
                checkResultDTOList.add(buildCheckResult(psecEntity, evalResult));
            }
        });

        return checkResultDTOList;
    }

    /**
     * 單一核保訊息 群組檢核 (群組內的條件為 AND 關係)
     *
     * @param ruleExpressionList 核保規則表
     * @param spEPDataDTO        檢核變數
     */
    private RuleEvalResultDTO evaluateGroup(List<RuleExpressionDTO> ruleExpressionList, SpEPDataDTO spEPDataDTO) {
        RuleEvalResultDTO groupResult = new RuleEvalResultDTO();
        groupResult.setMatched(true);
        Map<String, Object> simpleContext = new HashMap<>();
        List<Map<String, Object>> complexContexts = new ArrayList<>();

        for (RuleExpressionDTO ruleExpressionDTO : ruleExpressionList) {
            RuleEvalResultDTO ruleResult = evaluateExpression(ruleExpressionDTO, spEPDataDTO);
            simpleContext.putAll(ruleResult.getSimpleContext());
            complexContexts.addAll(ruleResult.getComplexContexts());
            if (!ruleResult.getMatched()) {
                // AND 關係，任一失敗則群組失敗
                groupResult.setMatched(false);
                return groupResult;
            }
        }
        groupResult.setSimpleContext(simpleContext);
        groupResult.setComplexContexts(complexContexts);

        return groupResult;
    }

    /**
     * 檢核單一規則
     *
     * @param ruleExpressionDTO 檢核規則
     * @param spEPDataDTO       檢核變數
     */
    private RuleEvalResultDTO evaluateExpression(RuleExpressionDTO ruleExpressionDTO, SpEPDataDTO spEPDataDTO) {
        String ruleModel = ruleExpressionDTO.getRuleModel();

        switch (RuleTypeEnum.getEnumByCode(ruleModel)) {
            // 基本模組
            case BASIC:
                Map<String, Object> basicData = spEPDataDTO.getBasicDataMap();
                if (basicData == null) {
                    basicData = new HashMap<>();
                }
                return spelRuleService.evaluateRule(
                        basicData,
                        ruleExpressionDTO.getExpression()
                );
            // 保障模組
            case COVERAGE:
                List<Map<String, Object>> coverageData = spEPDataDTO.getConverageDataMapList();
                if (coverageData == null) {
                    coverageData = new ArrayList<>();
                }
                return spelRuleService.evaluateRule(
                        coverageData,
                        ruleExpressionDTO.getExpression()
                );
            // 客戶模組
            case CLIENT:
                List<Map<String, Object>> clientData = spEPDataDTO.getClientDataMapList();
                if (clientData == null) {
                    clientData = new ArrayList<>();
                }
                return spelRuleService.evaluateRule(
                        clientData,
                        ruleExpressionDTO.getExpression()
                );
            default:
                RuleEvalResultDTO emptyResult = new RuleEvalResultDTO();
                emptyResult.setMatched(false);
                return emptyResult;
        }
    }

    /**
     * 建立檢核結果
     */
    private CheckResultDTO buildCheckResult(PsecDTO psecDTO, RuleEvalResultDTO evalResult) {
        // 產生 核保訊息文字
        List<String> messageList = new ArrayList<>();

        if (psecDTO.getRuleMessageDTO() != null) {
            RuleMessageDTO ruleMessageDTO = psecDTO.getRuleMessageDTO();

            switch (MessageTypeEnum.getEnumByCode(ruleMessageDTO.getMessageType())) {
                case SIMPLE:
                    String messageSimple = spelRuleService.generateMessages(evalResult.getSimpleContext(), ruleMessageDTO.getMessageTemplate(), psecDTO.getNbErrDesc());
                    messageList.add(messageSimple);
                case COMPLEX:
                    for (Map<String, Object> complexContext : evalResult.getComplexContexts()) {
                        String messageComplex = spelRuleService.generateMessages(complexContext, ruleMessageDTO.getMessageTemplate(), psecDTO.getNbErrDesc());
                        messageList.add(messageComplex);
                    }
            }
        } else {
            // 沒有模板，使用預設訊息
            messageList = Arrays.asList(psecDTO.getNbErrDesc());
        }

        // 輸出檢核結果
        CheckResultDTO checkResultDTO = new CheckResultDTO();
        checkResultDTO.setNbErrCode(psecDTO.getNbErrCode());
        checkResultDTO.setLevel(psecDTO.getSeverity()
        );
        checkResultDTO.setMessages(messageList);

        return checkResultDTO;
    }
}
