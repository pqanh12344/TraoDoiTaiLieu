package com.example.traodoitailieu.repositories;

import com.example.traodoitailieu.entities.Comment;
import com.example.traodoitailieu.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {

}
