package com.example.shop.product;

import com.example.shop.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    private BigDecimal price;
    @DocumentReference
    List<Category> categories;

    Product() {
    }

    public Product(ProductBuilder builder) {
        this(builder.id, builder.name, builder.sku, builder.url, builder.price, builder.categories);
    }

    public void setPrice(BigDecimal price) {
        if (price != null) {
            this.price = price.setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    public static final class ProductBuilder {

        private String id;
        private String name;

        private String sku;

        private String url;

        private BigDecimal price;

        private List<Category> categories;

        ProductBuilder() {
        }

        public ProductBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder setSku(String sku) {
            this.sku = sku;
            return this;
        }

        public ProductBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public ProductBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductBuilder setCategories(List<Category> categories) {
            this.categories = categories;
            return this;
        }

        Product build() {
            return new Product(this);
        }
    }
}
