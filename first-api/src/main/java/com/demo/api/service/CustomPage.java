package com.demo.api.service;

import java.util.List;

public class CustomPage<T> {
    private List<T> content;

    public CustomPage(List<T> content) {
        this.content = content;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
