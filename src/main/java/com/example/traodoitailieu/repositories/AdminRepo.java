package com.example.traodoitailieu.repositories;

import com.example.traodoitailieu.entities.Admin;
import com.example.traodoitailieu.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends CrudRepository<Admin, Integer> {

    @Query("SELECT u FROM Admin u WHERE u.user_name = :user_name and u.password = :password")
    Admin getAdmin(@Param("user_name") String user_name, @Param("password") String password);
}
