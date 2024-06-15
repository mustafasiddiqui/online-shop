package com.example.shop.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/category/{categoryName}")
    public Category getCategory(@PathVariable String categoryName) {
        return categoryService.getCategoryByName(categoryName);
    }

    @PostMapping("/category")
    public ResponseEntity<Void> addCategory(@RequestBody Category newCategory) {

        Category category = categoryService.getCategoryByName(newCategory.getName());
        if (category != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        categoryService.saveCategory(newCategory);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{categoryName}")
                .buildAndExpand(newCategory.getName())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
