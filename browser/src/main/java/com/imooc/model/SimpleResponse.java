package com.imooc.model;

public class SimpleResponse {

    private Object content;

    public SimpleResponse() {

    }

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
