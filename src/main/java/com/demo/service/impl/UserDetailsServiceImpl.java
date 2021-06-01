package com.demo.service.impl;

import com.demo.dto.UserPrincipalDto;
import com.demo.repository.UserRepository;
import com.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private WebApplicationContext applicationContext;
    private UserRepository userRepository;

    public UserDetailsServiceImpl(WebApplicationContext applicationContext, UserRepository userRepository) {
        this.applicationContext = applicationContext;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void completeSetup() {
        userRepository = applicationContext.getBean(UserRepository.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipalDto(user);
    }
}
