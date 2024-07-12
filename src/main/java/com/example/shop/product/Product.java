package com.example.shop.product;

import com.example.shop.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Document
public class Product {
    @MongoId
    private String id;

    private String name;

    private String sku;

    private String url;

    @DocumentReference
    List<Category> categories;

    Product() {
    }

    Product(String id, String name, String sku) {
        this(id, name, sku, null, null);
    }

}
