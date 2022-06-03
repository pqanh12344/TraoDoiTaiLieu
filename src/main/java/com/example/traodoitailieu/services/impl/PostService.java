package com.example.traodoitailieu.services.impl;

import com.example.traodoitailieu.entities.Comment;
import com.example.traodoitailieu.entities.Post;
import com.example.traodoitailieu.repositories.CommentRepo;
import com.example.traodoitailieu.repositories.PostRepo;
import com.example.traodoitailieu.services.ICommentService;
import com.example.traodoitailieu.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PostService implements IPostService {

    @Autowired
    PostRepo postRepo;

    @Override
    public ArrayList<Post> getAll(){
        ArrayList<Post> list = new ArrayList<>();
        Iterable it = postRepo.findAll();
        for(Object post: it){
            list.add((Post) post);
        }
        return list;
    }

    @Override
    public void savePost(Post post){
        Post savePost = new Post();
        savePost.setPost_id(post.getPost_id());
        savePost.setUser_id(post.getUser_id());
        savePost.setDescription(post.getDescription());
        savePost.setUpdated_up(post.getUpdated_up());
        postRepo.save(savePost);
    }

    @Override
    public Post getById(int id){
        return postRepo.findById(id).get();
    }

    @Override
    public void deleteById(int id){
        postRepo.deleteById(id);
    }
}
