package com.example.shop.category;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
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
}
