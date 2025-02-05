package com.example.loginsystem.services.impl;

import com.example.loginsystem.entities.User;
import com.example.loginsystem.exceptions.ResourceNotFoundException;
import com.example.loginsystem.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not found with email : " + username));

        return user;
    }
}
