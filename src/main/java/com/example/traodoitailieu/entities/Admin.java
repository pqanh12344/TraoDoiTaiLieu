package com.example.traodoitailieu.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "Admin")
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int admin_id;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "password")
    private String password;
}
