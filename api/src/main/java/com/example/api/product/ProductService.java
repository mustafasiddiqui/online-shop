package com.example.api.product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductByName(String productName);

    Product getProductById(String productId);

    Product saveProduct(Product product);

    void removeProductById(String productId);

    List<Product> getProductsByCategory(String categoryId);
}
