package com.example.traodoitailieu.services;

import com.example.traodoitailieu.entities.Admin;
import com.example.traodoitailieu.entities.User;

import java.util.ArrayList;

public interface IAdminService {
    ArrayList<Admin> getAll();
    Admin getAdmin(String user_name, String password);
}
