package com.example.traodoitailieu.services;

import com.example.traodoitailieu.entities.Role;
import com.example.traodoitailieu.entities.User_role;

import java.util.ArrayList;

public interface IUserRoleService {
    ArrayList<User_role> getAll();
    void saveUserRole(User_role user_role);
}
