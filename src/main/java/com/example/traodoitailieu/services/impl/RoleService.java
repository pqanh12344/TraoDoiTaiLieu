package com.example.traodoitailieu.services.impl;

import com.example.traodoitailieu.entities.Category;
import com.example.traodoitailieu.entities.Role;
import com.example.traodoitailieu.repositories.CategoryRepo;
import com.example.traodoitailieu.repositories.RoleRepo;
import com.example.traodoitailieu.services.ICategoryService;
import com.example.traodoitailieu.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoleService implements IRoleService {

    @Autowired
    RoleRepo roleRepo;

    @Override
    public ArrayList<Role> getAll(){
        ArrayList<Role> list = new ArrayList<>();
        Iterable it = roleRepo.findAll();
        for(Object role: it){
            list.add((Role) role);
        }
        return list;
    }

    @Override
    public void saveRole(Role role){
        Role saveRole = new Role();
        saveRole.setRole_id(role.getRole_id());
        saveRole.setRole_name(role.getRole_name());
        roleRepo.save(saveRole);
    }

    @Override
    public Role getById(int id){
        return roleRepo.findById(id).get();
    }

    @Override
    public void deleteById(int id){
        roleRepo.deleteById(id);
    }
}
