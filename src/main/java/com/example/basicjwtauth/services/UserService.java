package com.example.basicjwtauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.basicjwtauth.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // if (!username.equals("Ethan")) {
        //     throw new UsernameNotFoundException("Not Ethan");
        // }
        // Set<Role> roles = new HashSet<>();
        // roles.add(new Role(1L, "USER"));

        // return new ApplicationUser(1L, "Ethan", passwordEncoder.encode("password"), roles);
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User is not valid"));
    }
    
}
