package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    private String  userNo;

    private String fullName;

    private String username;

    private String password;

    private String userRole;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

}
