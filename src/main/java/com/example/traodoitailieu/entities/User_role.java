package com.example.traodoitailieu.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="User_role")
@Entity
public class User_role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "role_id")
    private int role_id;
}
