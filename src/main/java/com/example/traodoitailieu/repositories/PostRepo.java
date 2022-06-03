package com.example.traodoitailieu.repositories;

import com.example.traodoitailieu.entities.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends CrudRepository<Post, Integer> {

}
