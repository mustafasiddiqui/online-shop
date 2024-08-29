package com.example.api.category;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.mapping.DocumentPointer;

public class CategoryReferenceConverter implements Converter<Category, DocumentPointer<String>> {
    @Override
    public DocumentPointer<String> convert(Category source) {
        return () -> source.getId();
    }
}
