package com.example.traodoitailieu.repositories;

import com.example.traodoitailieu.entities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Integer> {

}
