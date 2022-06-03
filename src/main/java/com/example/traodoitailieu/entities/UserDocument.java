package com.example.traodoitailieu.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="userdocument")
public class UserDocument {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int user_document_id;

    @Column(name = "user_id")
    int user_id;

    @Column(name = "document_id")
    int document_id;

    @Column(name = "category_id")
    int category_id;
}
