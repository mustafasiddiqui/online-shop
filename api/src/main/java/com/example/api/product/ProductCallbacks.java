package com.example.api.product;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.stereotype.Component;

@Component
public class ProductCallbacks implements BeforeConvertCallback<Product> {
    @Override
    public Product onBeforeConvert(Product entity, String collection) {
        if (entity.getId() == null) {
            entity.setId(collection + "-" + new ObjectId());
        }

        return entity;
    }
}
