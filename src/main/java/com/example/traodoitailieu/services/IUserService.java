package com.example.traodoitailieu.services;

import com.example.traodoitailieu.entities.User;

import java.util.ArrayList;

public interface IUserService {
    ArrayList<User> getAll();
    boolean saveUser(User user);
    User getUser(String user_name, String password);
    User getById(int id);
    void deleteById(int id);
}
