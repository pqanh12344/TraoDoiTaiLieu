package com.example.traodoitailieu.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "Category")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

    @Column(name = "category_name")
    private String category_name;
}
