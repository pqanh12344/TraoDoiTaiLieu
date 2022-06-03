package com.example.traodoitailieu.services.impl;

import com.example.traodoitailieu.entities.Comment;
import com.example.traodoitailieu.repositories.CommentRepo;
import com.example.traodoitailieu.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentService implements ICommentService {

    @Autowired
    CommentRepo commentRepo;

    @Override
    public ArrayList<Comment> getAll(){
        ArrayList<Comment> list = new ArrayList<>();
        Iterable it = commentRepo.findAll();
        for(Object comment: it){
            list.add((Comment) comment);
        }
        return list;
    }

    @Override
    public void saveComment(Comment comment){
        Comment saveComment = new Comment();
        saveComment.setComment_id(comment.getComment_id());
        saveComment.setUser_id(comment.getUser_id());
        saveComment.setPost_id(comment.getPost_id());
        saveComment.setDescription(comment.getDescription());
        saveComment.setUpdated_up(comment.getUpdated_up());
        commentRepo.save(saveComment);
    }

    @Override
    public Comment getById(int id){
        return commentRepo.findById(id).get();
    }

    @Override
    public void deleteById(int id){
        commentRepo.deleteById(id);
    }
}
