package com.example.traodoitailieu.services;

import com.example.traodoitailieu.entities.Comment;
import com.example.traodoitailieu.entities.Post;

import java.util.ArrayList;

public interface IPostService {
    ArrayList<Post> getAll();
    void savePost(Post post);
    Post getById(int id);
    void deleteById(int id);
}
