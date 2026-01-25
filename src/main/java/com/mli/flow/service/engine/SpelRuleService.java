package com.mli.flow.service.engine;

import com.mli.flow.dto.engine.RuleEvalResultDTO;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SpEL 規則引擎 檢核模組
 */
@Service
public class SpelRuleService {
    private static final Logger log = LoggerFactory.getLogger(SpelRuleService.class);

    private final SpelExpressionParser PARSER = new SpelExpressionParser();
    private final Map<String, Expression> expressionCache = new ConcurrentHashMap<>();
    private final Integer EXPRESSION_MAX_SIZE = 1000;

    /**
     * 規則檢核：適用 基本模組 等 簡單模組
     *
     * @param dataMap  檢核變數
     * @param ruleCode 檢核規則
     * @return T.命中 / F.無命中
     */
    public RuleEvalResultDTO evaluateRule(Map<String, Object> dataMap, String ruleCode) {
        RuleEvalResultDTO ruleEvalResultDTO = new RuleEvalResultDTO();
        try {
            EvaluationContext context = new StandardEvaluationContext();
            dataMap.forEach(context::setVariable);
            // 評估表達式
            Expression expression = expressionCache.computeIfAbsent(ruleCode, code -> {
                // 檢查緩存大小，防止無限增長
                if (expressionCache.size() >= EXPRESSION_MAX_SIZE) {
                    log.warn("表達式緩存已達上限 {}，清除最舊的 20% 項目", EXPRESSION_MAX_SIZE);
                    evictOldestEntries(expressionCache);
                }
                return PARSER.parseExpression(code);
            });
            Boolean result = expression.getValue(context, Boolean.class);
            // 判斷結果處理
            ruleEvalResultDTO.setMatched(Boolean.TRUE.equals(result));
//            log.info("檢核規則={}, 檢核結果={}", expression, result);
            // 保存命中資料的檢核變數
            if (ruleEvalResultDTO.getMatched()) {
                ruleEvalResultDTO.setSimpleContext(dataMap);
            }
        } catch (Exception e) {
            String errorMessage = "Spel 檢核失敗，檢核規則: " + ruleCode + "，原因: " + e.getMessage();
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        }
        return ruleEvalResultDTO;
    }

    /**
     * 規則檢核：適用 客戶模組、保障模組 等 Foreach 類型模組
     *
     * @param dataMapList 檢核變數
     * @param ruleCode    檢核規則
     * @return T.命中 / F.無命中
     */
    public RuleEvalResultDTO evaluateRule(List<Map<String, Object>> dataMapList, String ruleCode) {
        RuleEvalResultDTO ruleEvalResultDTO = new RuleEvalResultDTO();
        try {
            List<Map<String, Object>> complexContext = new ArrayList<>();
            for (Map<String, Object> dataMap : dataMapList) {
                EvaluationContext context = new StandardEvaluationContext();
                dataMap.forEach(context::setVariable);
                // 評估表達式
                Expression expression = expressionCache.computeIfAbsent(ruleCode, code -> {
                    // 檢查緩存大小，防止無限增長
                    if (expressionCache.size() >= EXPRESSION_MAX_SIZE) {
                        log.warn("表達式緩存已達上限 {}，清除最舊的 20% 項目", EXPRESSION_MAX_SIZE);
                        evictOldestEntries(expressionCache);
                    }
                    return PARSER.parseExpression(code);
                });
                Boolean result = expression.getValue(context, Boolean.class);
//                log.info("檢核規則={}, 檢核結果={}", expression, result);
                // 保存命中資料的檢核變數
                if (Boolean.TRUE.equals(result)) {
                    ruleEvalResultDTO.setMatched(true);
                    complexContext.add(dataMap);
                }
            }
            ruleEvalResultDTO.setComplexContexts(complexContext);
        } catch (Exception e) {
            String errorMessage = "Spel 檢核失敗，檢核規則: " + ruleCode + "，原因: " + e.getMessage();
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        }
        return ruleEvalResultDTO;
    }

    /**
     * 產生核保訊息文字
     *
     * @param dataMap   檢核變數
     * @param ruleCode  檢核規則
     * @param nbErrDesc 核保訊息文字
     */
    public String generateMessages(Map<String, Object> dataMap, String ruleCode, String nbErrDesc) {
        dataMap.put("nbErrDesc", nbErrDesc);

        String message = "";
        try {
            EvaluationContext context = new StandardEvaluationContext();
            dataMap.forEach(context::setVariable);
            // 評估表達式
            Expression expression = expressionCache.computeIfAbsent(ruleCode, code -> {
                // 檢查緩存大小，防止無限增長
                if (expressionCache.size() >= EXPRESSION_MAX_SIZE) {
                    log.warn("表達式緩存已達上限 {}，清除最舊的 20% 項目", EXPRESSION_MAX_SIZE);
                    evictOldestEntries(expressionCache);
                }
                return PARSER.parseExpression(code);
            });
            message = expression.getValue(context, String.class);
        } catch (Exception e) {
            String errorMessage = "Spel 檢核失敗，檢核規則: " + ruleCode + "，原因: " + e.getMessage();
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        }
        return message;
    }

    /**
     * 當緩存滿時，清除最舊的 20% 項目
     */
    private void evictOldestEntries(Map<String, Expression> expressionCache) {
        int removeCount = EXPRESSION_MAX_SIZE / 5; // 清除 20%
        Iterator<String> iterator = expressionCache.keySet().iterator();

        int removed = 0;
        while (iterator.hasNext() && removed < removeCount) {
            iterator.next();
            iterator.remove();
            removed++;
        }

        log.info("已清除 {} 個舊的表達式緩存項目", removed);
    }
}
