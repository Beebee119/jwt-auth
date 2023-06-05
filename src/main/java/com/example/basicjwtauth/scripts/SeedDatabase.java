package com.example.basicjwtauth.scripts;

import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.basicjwtauth.models.ApplicationUser;
import com.example.basicjwtauth.models.Role;
import com.example.basicjwtauth.repositories.RoleRepository;
import com.example.basicjwtauth.repositories.UserRepository;

@Configuration
public class SeedDatabase {
    private static final Logger logger = LoggerFactory.getLogger(SeedDatabase.class);

    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("ADMIN"));
            logger.info("Perloading " + adminRole);
            logger.info("Perloading " + roleRepository.save(new Role("USER")));
            
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            logger.info("Perloading " + userRepository.save(new ApplicationUser("admin", passwordEncoder.encode("password"), roles)));
        };
    }
}
