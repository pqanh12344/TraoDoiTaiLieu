package com.example.traodoitailieu.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name="Role")
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int post_id;

    @Column(name = "role_id")
    private int role_id;

    @Column(name = "role_name")
    private String role_name;
}
