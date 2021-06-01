package com.demo.service.impl;

import com.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final String FIND_SUCCESS = "User find successfully";
    private static final String CREATE_SUCCESS = "User create successfully";
    private static final String UPDATE_SUCCESS = "User update successfully";
    private static final String DELETE_SUCCESS = "User delete successfully";
    private static final String NOT_FOUND = "User with id %s not found!";
}
