package com.example.traodoitailieu.services.impl;

import com.example.traodoitailieu.entities.Role;
import com.example.traodoitailieu.entities.User;
import com.example.traodoitailieu.repositories.RoleRepo;
import com.example.traodoitailieu.repositories.UserRepo;
import com.example.traodoitailieu.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Override
    public ArrayList<User> getAll(){
        ArrayList<User> list = new ArrayList<>();
        Iterable it = userRepo.findAll();
        for(Object user: it){
            list.add((User) user);
        }
        return list;
    }

    @Override
    public boolean saveUser(User user){
        try{
            User saveUser = new User();
            saveUser.setUser_id(user.getUser_id());
            saveUser.setUser_name(user.getUser_name());
            saveUser.setName(user.getName());
            saveUser.setEmail(user.getEmail());
            saveUser.setPassword(user.getPassword());
            /*Role role = roleRepo.findById(user.getRoleId()).get();
            HashSet<Role> roles = new HashSet<>();
            roles.add(role);

            saveUser.setRoles(roles);*/
            userRepo.save(saveUser);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public User getUser(String user_name, String password){
        return userRepo.getUser(user_name, password);
    }

    @Override
    public User getById(int id){
        return userRepo.findById(id).get();
    }

    @Override
    public void deleteById(int id){
        userRepo.deleteById(id);
    }
}
