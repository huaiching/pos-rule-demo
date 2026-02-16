package com.mli.flow.model.lifeChagne.constants;

public enum RuleTypeEnum {
    /** 1.基本模組 */
    BASIC("1", "基本模組"),
    /** 2.保障模組 - 已變更後保障進行分組 */
    COVERAGE("2", "保障模組"),
    /** 3.客戶模組 - 已客戶關係碼進行分組 */
    CLIENT("3", "客戶模組"),
    /** 4.受益人模組 - 已變更後受益人進行分組 */
    BENEFIT("4","受益人模組"),
    /** 5.投資模組 - 已變更後投資標的進行分組 */
    INVEST("5","投資模組"),
    /** 6.累計保額模組 - 已變更後 被保人 ID 進行分組 */
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

    public static RuleTypeEnum getEnumByCode(String code) {
        for (RuleTypeEnum type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未知的規則類型: " + code);
    }

}
