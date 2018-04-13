package com.lyu.tech.common.config.websocket;

public class OutMessage {

    private String content;

    public OutMessage() {
    }

    public OutMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
