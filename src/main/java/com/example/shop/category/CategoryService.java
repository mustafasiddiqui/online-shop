package com.example.shop.category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryByName(String categoryName);
}
