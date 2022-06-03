package com.example.traodoitailieu.services.impl;

import com.example.traodoitailieu.entities.Admin;
import com.example.traodoitailieu.repositories.AdminRepo;
import com.example.traodoitailieu.services.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminService implements IAdminService {

    @Autowired
    AdminRepo adminRepo;

    @Override
    public ArrayList<Admin> getAll(){
        ArrayList<Admin> list = new ArrayList<>();
        Iterable it = adminRepo.findAll();
        for(Object admin: it){
            list.add((Admin) admin);
        }
        return list;
    }

    @Override
    public Admin getAdmin(String user_name, String password){
        return adminRepo.getAdmin(user_name, password);
    }
}
