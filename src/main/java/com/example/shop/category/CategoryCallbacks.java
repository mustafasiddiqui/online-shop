package com.example.shop.category;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertCallback;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveCallback;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CategoryCallbacks implements BeforeConvertCallback<Category> {

    @Override
    public Category onBeforeConvert(Category entity, String collection) {
        entity.setId(collection + "-" + new ObjectId());
        return entity;
    }
}
