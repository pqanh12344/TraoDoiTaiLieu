package com.example.traodoitailieu.controllers.admin;

import com.example.traodoitailieu.entities.Role;
import com.example.traodoitailieu.entities.User;
import com.example.traodoitailieu.entities.User_role;
import com.example.traodoitailieu.services.UserRoleService;
import com.example.traodoitailieu.services.impl.RoleService;
import com.example.traodoitailieu.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @GetMapping("")
    public String list(Model model){
        ArrayList<User> list = new ArrayList<>();
        ArrayList<User> a = userService.getAll();
        Set<Integer> b = new HashSet<>();
        ArrayList<User_role> c = userRoleService.getAll();
        ArrayList<Role> d = roleService.getAll();
        for (User_role m: c) {
            for (Role k: d) {
                if(m.getRole_id() == k.getRole_id() && k.getRole_name().equals("ROLE_ADMIN")){
                    b.add(m.getUser_id());
                }
            }
        }
        for (User res: a) {
            for (int p: b) {
                if(res.getUser_id() == p){
                    list.add(res);
                }
            }
        }
        model.addAttribute("list", list);
        return "admin/admin/list";
    }
}
