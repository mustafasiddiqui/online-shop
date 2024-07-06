package com.example.shop.product;

import com.example.shop.category.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public List<Product> getAllProducts(@RequestParam(required = false) String categoryId) {
        if (categoryId != null) {
            return productService.getProductsByCategory(categoryId);
        }
        return productService.getAllProducts();
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/product")
    public ResponseEntity<Void> addProduct(@RequestBody Product newProduct) {

        Product product = productService.getProductByName(newProduct.getName());
        if (product != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        productService.saveProduct(newProduct);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{productId}")
                .buildAndExpand(newProduct.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<Void> updateCategory(@PathVariable String productId, @RequestBody Product updatedProduct) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        copy(updatedProduct, product);
        productService.saveProduct(product);

        return ResponseEntity.noContent().build();
    }

    private void copy(Product source, Product target) {
        target.setName(source.getName());
        target.setSku(source.getSku());
        target.setCategories(source.getCategories());
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable String productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        productService.removeProductById(productId);
        return ResponseEntity.noContent().build();
    }
}
