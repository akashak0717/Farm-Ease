package com.nyinst.farmease.model;

public class CategoryListModel {
    int id;
    String categoryName;

    public CategoryListModel(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public String getCategoryName() {return categoryName;}

    public void setCategoryName(String content) {
        this.categoryName = categoryName;
    }

}
