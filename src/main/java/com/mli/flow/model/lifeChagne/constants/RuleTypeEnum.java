package com.mli.flow.model.lifeChagne.constants;

public enum RuleTypeEnum {
    BASIC("1", "基本模組"),
    COVERAGE("2", "保障模組"),
    CLIENT("3", "客戶模組"),
    BENEFIT("4","受益人模組"),
    INVEST("5","投資模組"),
    TOTAL_FACE_AMT("6","累計保額模組");

    private final String code;
    private final String description;

    RuleTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static RuleTypeEnum fromCode(String code) {
        for (RuleTypeEnum type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未知的規則類型: " + code);
    }

}
