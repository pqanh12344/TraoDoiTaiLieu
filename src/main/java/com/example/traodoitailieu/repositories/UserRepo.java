package com.example.traodoitailieu.repositories;

import com.example.traodoitailieu.entities.User;
import com.example.traodoitailieu.entities.User_role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.user_name = :user_name and u.password = :password")
    User getUser(@Param("user_name") String user_name, @Param("password") String password);

    @Query("SELECT u FROM User u WHERE u.user_name = :user_name")
    User getUsername(@Param("user_name") String user_name);

    @Query("SELECT ur.role_id FROM User_role ur WHERE ur.user_id = :user_id")
    List<Integer> getRoleNames(@Param("user_id") int user_id);
}
