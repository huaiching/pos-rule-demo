package com.mli.flow.service.engine;

import com.mli.flow.constants.MessageTypeEnum;
import com.mli.flow.constants.RuleTypeEnum;
import com.mli.flow.dto.CheckResultDTO;
import com.mli.flow.dto.RuleEvalResultDTO;
import com.mli.flow.entity.PosRuleEntity;
import com.mli.flow.entity.PosRuleMessageEntity;
import com.mli.flow.entity.PsecEntity;
import com.mli.flow.repository.PosRuleMessageRepository;
import com.mli.flow.repository.PosRuleRepository;
import com.mli.flow.repository.PsecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CheckService {
    @Autowired
    private PsecRepository psecRepository;
    @Autowired
    private PosRuleRepository posRuleRepository;
    @Autowired
    private SpelRuleService spelRuleService;
    @Autowired
    private PosRuleMessageRepository posRuleMessageRepository;

    /**
     * 執行核保訊息檢核
     *
     * @param dataMap 檢核變數
     */
    public List<CheckResultDTO> execute(Map<String, Object> dataMap) {
        List<CheckResultDTO> results = new ArrayList<>();
        // 取得所有核保規則
        List<PsecEntity> psecList = psecRepository.findAll();
        // 取得所有檢核規則
        List<PosRuleEntity> allRules = posRuleRepository.findAll();

        // 逐一進行核保檢核
        for (PsecEntity psec : psecList) {
            CheckResultDTO result = evaluateRule(psec, allRules, dataMap);
            if (result != null) {
                results.add(result);
            }
        }

        return results;
    }

    /**
     * 單一核保訊息檢核
     *
     * @param psecEntity 核保訊息表
     * @param allRules   核保規則表
     * @param dataMap    檢核變數
     */
    private CheckResultDTO evaluateRule(PsecEntity psecEntity, List<PosRuleEntity> allRules, Map<String, Object> dataMap) {
        // 取得 要檢核的規則
        List<PosRuleEntity> checkRuleList = allRules.stream()
                .filter(rule -> rule.getNbErrCode().equals(psecEntity.getNbErrCode()))
                .collect(Collectors.toList());
        if (checkRuleList.isEmpty()) {
            return null;
        }

        // 取得所有群組 (OR 關係)
        List<String> groupCodeList = checkRuleList.stream()
                .map(PosRuleEntity::getGroupCode).distinct()
                .collect(Collectors.toList());

        // 依照群組進行檢核：任一群組成立即成立
        for (String groupCode : groupCodeList) {
            // 取得 該群組的 檢核規則
            List<PosRuleEntity> groupRules = checkRuleList.stream()
                    .filter(rule -> rule.getGroupCode().equals(groupCode))
                    .collect(Collectors.toList());
            // 執行檢核
            RuleEvalResultDTO evalResult = evaluateGroup(groupRules, dataMap);

            if (evalResult.getMatched()) {
                // 群組命中，生成結果
                return buildCheckResult(psecEntity, evalResult);
            }
        }

        return null;
    }

    /**
     * 單一核保訊息 群組檢核 (群組內的條件為 AND 關係)
     *
     * @param allRules 核保規則表
     * @param dataMap  檢核變數
     */
    private RuleEvalResultDTO evaluateGroup(List<PosRuleEntity> allRules, Map<String, Object> dataMap) {
        RuleEvalResultDTO groupResult = new RuleEvalResultDTO();
        groupResult.setMatched(true);
        Map<String, Object> simpleContext = new HashMap<>();
        List<Map<String, Object>> complexContexts = new ArrayList<>();

        for (PosRuleEntity rule : allRules) {
            RuleEvalResultDTO ruleResult = evaluateExpression(rule, dataMap);
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
     * @param rule    檢核規則
     * @param dataMap 檢核變數
     * @return
     */
    private RuleEvalResultDTO evaluateExpression(PosRuleEntity rule, Map<String, Object> dataMap) {
        String ruleModel = rule.getRuleModel();

        switch (RuleTypeEnum.fromCode(ruleModel)) {
            // 基本模組
            case BASIC:
                Map<String, Object> basicData = (Map<String, Object>) dataMap.get("basic");
                if (basicData == null) {
                    basicData = new HashMap<>();
                }
                return spelRuleService.evaluateRule(
                        basicData,
                        rule.getExpression()
                );
            // 保障模組
            case COVERAGE:
                List<Map<String, Object>> coverageData =
                        (List<Map<String, Object>>) dataMap.get("coverage");
                if (coverageData == null) {
                    coverageData = new ArrayList<>();
                }
                return spelRuleService.evaluateRule(
                        coverageData,
                        rule.getExpression()
                );
            // 客戶模組
            case CLIENT:
                List<Map<String, Object>> clientData =
                        (List<Map<String, Object>>) dataMap.get("client");
                if (clientData == null) {
                    clientData = new ArrayList<>();
                }
                return spelRuleService.evaluateRule(
                        clientData,
                        rule.getExpression()
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
    private CheckResultDTO buildCheckResult(PsecEntity psec, RuleEvalResultDTO evalResult) {
        // 查詢訊息模板
        Optional<PosRuleMessageEntity> messageEntityOpt =
                posRuleMessageRepository.findById(psec.getNbErrCode());

        // 產生 核保訊息文字
        List<String> messageList = new ArrayList<>();

        if (messageEntityOpt.isPresent()) {
            PosRuleMessageEntity messageEntity = messageEntityOpt.get();

            switch (MessageTypeEnum.fromCode(messageEntity.getMessageType())) {
                case SIMPLE:
                    String messageSimple = spelRuleService.generateMessages(evalResult.getSimpleContext(), messageEntity.getMessageTemplate(), psec.getNbErrDesc());
                    messageList.add(messageSimple);
                case COMPLEX:
                    for (Map<String, Object> complexContext : evalResult.getComplexContexts()) {
                        String messageComplex = spelRuleService.generateMessages(complexContext, messageEntity.getMessageTemplate(), psec.getNbErrDesc());
                        messageList.add(messageComplex);
                    }
            }
        } else {
            // 沒有模板，使用預設訊息
            messageList = Arrays.asList(psec.getNbErrDesc());
        }

        // 輸出檢核結果
        CheckResultDTO checkResultDTO = new CheckResultDTO();
        checkResultDTO.setNbErrCode(psec.getNbErrCode());
        checkResultDTO.setLevel(psec.getLevel());
        checkResultDTO.setMessages(messageList);

        return checkResultDTO;
    }
}
