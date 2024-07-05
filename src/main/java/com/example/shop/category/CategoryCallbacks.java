package com.example.shop.category;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.stereotype.Component;

@Component
public class CategoryCallbacks implements BeforeConvertCallback<Category> {

    @Override
    public Category onBeforeConvert(Category entity, String collection) {
        if (entity.getId() == null) {
            entity.setId(collection + "-" + new ObjectId());
        }

        return entity;
    }
}
