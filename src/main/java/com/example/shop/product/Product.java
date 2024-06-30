package com.example.shop.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Document
public class Product {
    @Id
    private String id;

    private String name;

    private String sku;
    
    private String categoryId;

}
