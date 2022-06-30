package com.example.traodoitailieu.services;

import com.example.traodoitailieu.entities.Category;
import com.example.traodoitailieu.entities.Role;

import java.util.ArrayList;

public interface IRoleService {
    ArrayList<Role> getAll();
    void saveRole(Role role);
    Role getById(int id);
    void deleteById(int id);
}
