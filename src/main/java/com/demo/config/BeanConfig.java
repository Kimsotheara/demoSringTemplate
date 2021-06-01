package com.demo.config;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfig {
    private UserRepository userRepository;

    public BeanConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public void createDefaultUser() {
        User existingUser = this.userRepository.findByUsername("admin");
        if (existingUser == null) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(this.encoder().encode("password"));
            user.setUserRole("ADMIN");
            this.userRepository.save(user);
        }
    }

}
