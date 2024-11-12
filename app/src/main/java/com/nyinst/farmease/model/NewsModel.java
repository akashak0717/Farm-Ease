package com.nyinst.farmease.model;

public class NewsModel {
    String title;
    String content;
    String createBy;

    public NewsModel(String title, String content, String createBy) {
        this.title = title;
        this.content = content;
        this.createBy = createBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
