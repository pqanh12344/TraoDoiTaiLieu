package com.example.traodoitailieu.controllers.web;

import com.example.traodoitailieu.entities.Category;
import com.example.traodoitailieu.entities.Role;
import com.example.traodoitailieu.entities.User;
import com.example.traodoitailieu.entities.User_role;
import com.example.traodoitailieu.services.UserRoleService;
import com.example.traodoitailieu.services.impl.CategoryService;
import com.example.traodoitailieu.services.impl.RoleService;
import com.example.traodoitailieu.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @GetMapping("/home")
    public String Home(Model model, HttpServletRequest request, HttpServletResponse response) {
        ArrayList<User> list123 = new ArrayList<>();
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
                    list123.add(res);
                }
            }
        }

        int checkCookie = 0;
        ArrayList<Category> list = categoryService.getAll();
        model.addAttribute("list_category", list);
        boolean check = false;
        int user_id = 0;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        if(cookies == null){
            return "web/user/home";
        }else{
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if(cookie.getName().equals(cookieName)){
                    user_id = Integer.parseInt(cookie.getValue());
                    check = true;
                    break;
                }
            }
            if(check == true){
                checkCookie = 1;
                model.addAttribute("checkCookie", checkCookie);
                /*for (User g: list123) {
                    if(g.getUser_id() == user_id){
                        return "web/user/home1";
                    }
                }*/
                return "web/user/home";
            }
        }
        return "web/user/home";
    }

    @GetMapping("/a")
    public String Homes(Model model) {
        int k = 1;
        model.addAttribute("k", "gbvsxgbsdgv");
        return "test";
    }
}
