package com.example.traodoitailieu.repositories;

import com.example.traodoitailieu.entities.Role;
import com.example.traodoitailieu.entities.User_role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends CrudRepository<User_role, Integer> {

}
