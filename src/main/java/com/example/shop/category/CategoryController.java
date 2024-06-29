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

    @GetMapping("/category/{categoryId}")
    public Category getCategory(@PathVariable String categoryId) {
        return categoryService.getCategoryById(categoryId);
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

    @PutMapping("/category/{categoryName}")
    public ResponseEntity<Void> updateCategory(@PathVariable String categoryName, @RequestBody Category updatedCategory) {
        Category category = categoryService.getCategoryByName(categoryName);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        category.setName(updatedCategory.getName());
        category.setParentId(updatedCategory.getParentId());

        categoryService.saveCategory(category);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/category/{categoryName}")
    public ResponseEntity<Void> removeCategory(@PathVariable String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        categoryService.removeCategoryByName(categoryName);
        return ResponseEntity.noContent().build();
    }
}
