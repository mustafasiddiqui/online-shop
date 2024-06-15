package com.example.shop.category;

import org.springframework.data.annotation.Id;

public class Category {
    @Id
    private String id;
    private String name;
    private String parentId;

    public Category() {
    }

    public Category(String name) {
        this(name, null);
    }

    public Category(String name, String parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return String.format("Category [id=%1$s, name=%2$s, parentId=%3$s]", id, name, parentId);
    }
}
