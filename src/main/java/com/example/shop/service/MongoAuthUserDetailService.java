package com.example.shop.service;

import com.example.shop.user.ShopUser;
import com.example.shop.user.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MongoAuthUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public MongoAuthUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ShopUser user = userRepository.findByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        user.getAuthorities()
                .forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().getName())));

        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
