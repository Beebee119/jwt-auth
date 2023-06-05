package com.example.basicjwtauth.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.basicjwtauth.models.ApplicationUser;
import com.example.basicjwtauth.models.Role;
import com.example.basicjwtauth.repositories.RoleRepository;
import com.example.basicjwtauth.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthenticationService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApplicationUser registerUser(String username, String password) {
        
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        return userRepository.save(new ApplicationUser(username, encodedPassword, authorities));
    }

}
