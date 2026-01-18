package com.mli.flow.constants;

public enum MessageTypeEnum {
    SIMPLE("1", "簡單類型"),
    COMPLEX("2", "複雜類型");

    private final String code;
    private final String description;

    MessageTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static MessageTypeEnum fromCode(String code) {
        for (MessageTypeEnum type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未知的訊息類型: " + code);
    }
}
