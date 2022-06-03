package com.example.traodoitailieu.services;

import com.example.traodoitailieu.entities.Category;
import com.example.traodoitailieu.entities.Comment;

import java.util.ArrayList;

public interface ICommentService {
    ArrayList<Comment> getAll();
    void saveComment(Comment comment);
    Comment getById(int id);
    void deleteById(int id);
}
