package com.example.shop.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Document
public class ShopUser implements UserDetails {

    @Getter
    private @MongoId ObjectId id;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    @JsonIgnore
    private String password;
    @Getter
    @Setter
    private Set<UserRole> userRoles;

    @Override
    public Set<UserRole> getAuthorities() {
        return this.userRoles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
