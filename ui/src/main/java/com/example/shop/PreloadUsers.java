package com.example.shop;

import com.example.shop.user.Role;
import com.example.shop.user.ShopUser;
import com.example.shop.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;

@Component
public class PreloadUsers implements CommandLineRunner {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void run(String... args) throws Exception {

        mongoTemplate.dropCollection(ShopUser.class);
        mongoTemplate.dropCollection(Role.class);

        saveUserWithRole("user", "user", "ROLE_USER");
        saveUserWithRole("admin", "admin", "ROLE_ADMIN");
        saveUserWithRole("guest","guest","ROLE_GUEST");
    }

    private void saveUserWithRole(String username, String password, String roleName) {
        Role roleUser = new Role();
        roleUser.setName(roleName);
        mongoTemplate.save(roleUser);

        ShopUser user = new ShopUser();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        UserRole userRole = new UserRole();
        userRole.setRole(roleUser);
        user.setUserRoles(new HashSet<>(Collections.singletonList(userRole)));
        mongoTemplate.save(user);
    }
}
