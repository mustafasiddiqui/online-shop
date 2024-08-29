package com.example.api.config;

import com.example.api.category.CategoryReferenceConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import static org.springframework.data.mongodb.core.convert.MongoCustomConversions.MongoConverterConfigurationAdapter;

@Configuration
public class MongoDbConfiguration extends AbstractMongoClientConfiguration {
    @Override
    public String getDatabaseName() {
        return "shop";
    }

    @Override
    protected void configureConverters(MongoConverterConfigurationAdapter adapter) {
        adapter.registerConverter(new CategoryReferenceConverter());
    }
}
