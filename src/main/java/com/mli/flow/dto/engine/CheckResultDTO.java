package com.mli.flow.dto.engine;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "核保檢核結果")
public class CheckResultDTO {
    private String nbErrCode;                   // 核保訊息代碼
    private String level;                       // 核保等級
    private List<String> messages;              // 訊息列表

    public String getNbErrCode() {
        return nbErrCode;
    }

    public void setNbErrCode(String nbErrCode) {
        this.nbErrCode = nbErrCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
