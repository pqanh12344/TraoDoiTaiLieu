package com.example.traodoitailieu.services;

import com.example.traodoitailieu.entities.Role;
import com.example.traodoitailieu.entities.User_role;
import com.example.traodoitailieu.repositories.RoleRepo;
import com.example.traodoitailieu.repositories.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserRoleService implements IUserRoleService {

    @Autowired
    UserRoleRepo userRoleRepo;

    @Override
    public ArrayList<User_role> getAll(){
        ArrayList<User_role> list = new ArrayList<>();
        Iterable it = userRoleRepo.findAll();
        for(Object role: it){
            list.add((User_role) role);
        }
        return list;
    }

    @Override
    public void saveUserRole(User_role user_role){
        User_role saveUserRole = new User_role();
        saveUserRole.setRole_id(user_role.getRole_id());
        saveUserRole.setUser_id(user_role.getUser_id());
        userRoleRepo.save(saveUserRole);
    }
}
