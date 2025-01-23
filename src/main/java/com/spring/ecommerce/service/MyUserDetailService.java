package com.spring.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.ecommerce.model.User;
import com.spring.ecommerce.model.UserPrincipal;
import com.spring.ecommerce.repository.UserRepo;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userrepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userrepo.findByUsername(username);

        if (user == null) {
            System.out.println("User 404");
            throw new UsernameNotFoundException("user 404");
        }
        return new UserPrincipal(user);
    }

}
