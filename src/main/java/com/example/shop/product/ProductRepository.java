package com.example.shop.product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByName(String productName);

    void deleteByName(String productName);
}
