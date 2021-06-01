package com.demo.controller;

import com.demo.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class UserController {

    private UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("mUserOpen", "menu-open");
        model.addAttribute("mULActive", "active");
        return "layouts/user/list";
    }
}
