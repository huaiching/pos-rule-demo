package com.mli.flow.service.engine;

import com.mli.flow.dto.RuleEvalResultDTO;
import com.mli.flow.exceptions.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SpEL 規則引擎 檢核模組
 */
@Service
public class SpelRuleService {
    private static final Logger log = LoggerFactory.getLogger(SpelRuleService.class);

    private final SpelExpressionParser parser = new SpelExpressionParser();

    /**
     * 規則檢核：適用 基本模組 等 簡單模組
     *
     * @param dataMap    檢核變數
     * @param expression 檢核規則
     * @return T.命中 / F.無命中
     */
    public RuleEvalResultDTO evaluateRule(Map<String, Object> dataMap, String expression) {
        RuleEvalResultDTO ruleEvalResultDTO = new RuleEvalResultDTO();
        try {
            EvaluationContext context = new StandardEvaluationContext();
            dataMap.forEach(context::setVariable);
            // 評估表達式
            Expression exp = parser.parseExpression(expression);
            Boolean result = exp.getValue(context, Boolean.class);
            ruleEvalResultDTO.setMatched(Boolean.TRUE.equals(result));
            log.debug("檢核規則={}, 檢核結果={}", expression, result);
            // 保存命中資料的檢核變數
            if (ruleEvalResultDTO.getMatched()) {
                ruleEvalResultDTO.setSimpleContext(dataMap);
            }
        } catch (Exception e) {
            String errorMessage = "Spel 檢核失敗，檢核規則: " + expression + "，原因: " + e.getMessage();
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        }
        return ruleEvalResultDTO;
    }

    /**
     * 規則檢核：適用 客戶模組、保障模組 等 Foreach 類型模組
     *
     * @param dataMapList 檢核變數
     * @param expression  檢核規則
     * @return T.命中 / F.無命中
     */
    public RuleEvalResultDTO evaluateRule(List<Map<String, Object>> dataMapList, String expression) {
        RuleEvalResultDTO ruleEvalResultDTO = new RuleEvalResultDTO();
        try {
            List<Map<String, Object>> complexContext = new ArrayList<>();
            for (Map<String, Object> dataMap : dataMapList) {
                EvaluationContext context = new StandardEvaluationContext();
                dataMap.forEach(context::setVariable);
                // 評估表達式
                Expression exp = parser.parseExpression(expression);
                Boolean result = exp.getValue(context, Boolean.class);
                log.debug("檢核規則={}, 檢核結果={}", expression, result);
                // 保存命中資料的檢核變數
                if (Boolean.TRUE.equals(result)) {
                    ruleEvalResultDTO.setMatched(true);
                    complexContext.add(dataMap);
                }
            }
            ruleEvalResultDTO.setComplexContexts(complexContext);
        } catch (Exception e) {
            String errorMessage = "Spel 檢核失敗，檢核規則: " + expression + "，原因: " + e.getMessage();
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        }
        return ruleEvalResultDTO;
    }

    /**
     * 產生核保訊息文字
     *
     * @param dataMap    檢核變數
     * @param expression 檢核規則
     * @param nbErrDesc  核保訊息文字
     */
    public String generateMessages(Map<String, Object> dataMap, String expression, String nbErrDesc) {
        dataMap.put("nbErrDesc", nbErrDesc);

        String message = "";
        try {
            EvaluationContext context = new StandardEvaluationContext();
            dataMap.forEach(context::setVariable);
            // 評估表達式
            Expression exp = parser.parseExpression(expression);
            message = exp.getValue(context, String.class);
        } catch (Exception e) {
            String errorMessage = "Spel 檢核失敗，檢核規則: " + expression + "，原因: " + e.getMessage();
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        }
        return message;
    }
}
