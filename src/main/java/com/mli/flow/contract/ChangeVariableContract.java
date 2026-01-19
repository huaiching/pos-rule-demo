package com.mli.flow.contract;

import java.util.Map;

/**
 * 核保引擎 前置變數設定
 */
public interface ChangeVariableContract {
    void execute(Map<String, Object> dataMap);
}
