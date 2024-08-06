package com.example.shop.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority {

    @Getter
    @Setter
    private Role role;

    @Override
    public String getAuthority() {
        return role.getName();
    }
}
