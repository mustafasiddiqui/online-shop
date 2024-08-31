package com.example.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoDbConfiguration extends AbstractMongoClientConfiguration {
    @Override
    public String getDatabaseName() {
        return "shop";
    }

}
