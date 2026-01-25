package com.mli.flow.contract;

import java.util.Map;

/**
 * 核保引擎: Common 規則變數 的 方法定義
 */
public interface ChangeVariableContract {
    void execute(Map<String, Object> dataMap);
}
