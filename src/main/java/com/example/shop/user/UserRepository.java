package com.example.shop.user;

import com.example.shop.user.ShopUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<ShopUser, String> {

    ShopUser findByUsername(String userName);
}
