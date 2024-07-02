package com.example.shop.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@AllArgsConstructor
@Document
public class Category {
    @MongoId
    private String id;
    private String name;
    private String parentId;

    public Category() {
    }

    public Category(String name) {
        this(name, null);
    }

    public Category(String name, String parentId) {
        this(null, name, parentId);
    }
}
