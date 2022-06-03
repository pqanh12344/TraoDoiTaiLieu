package com.example.traodoitailieu.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name="Post")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int post_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "description")
    private String description;

    @Column(name = "updated_up")
    private Date updated_up;
}
