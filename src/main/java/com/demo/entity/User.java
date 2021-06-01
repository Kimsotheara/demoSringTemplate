package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class  User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_no")
    private String userNo;

    @Column(name = "full_name", unique = true)
    private String fullName;

    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "password" ,unique = true)
    private String password;

    @Column(name = "user_role",nullable = false)
    private String userRole;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createdDate = new Date();

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date updatedAt = new Date();

}
