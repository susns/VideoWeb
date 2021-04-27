package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    private String uid;
    @Column(name = "user_name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "user_avatar")
    private String head;
}
