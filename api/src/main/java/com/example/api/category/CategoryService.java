package com.example.api.category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryByName(String categoryName);

    Category getCategoryById(String categoryId);

    Category saveCategory(Category category);

    void removeCategoryById(String categoryId);
}
