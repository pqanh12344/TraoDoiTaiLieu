package com.example.traodoitailieu.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name="Comment")
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "post_id")
    private int post_id;

    @Column(name = "description")
    private String description;

    @Column(name = "updated_up")
    private Date updated_up;
}
