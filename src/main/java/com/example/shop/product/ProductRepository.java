package com.example.shop.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByName(String productName);

    @Query("{ 'categories.id' : ?0 }")
    List<Product> findByCategoryId(String categoryId);

    void deleteByName(String productName);
}
