package com.example.traodoitailieu.controllers.web;

import com.example.traodoitailieu.entities.Category;
import com.example.traodoitailieu.services.impl.CategoryService;
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

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/home")
    public String Home(Model model, HttpServletRequest request, HttpServletResponse response) {
        int checkCookie = 0;
        ArrayList<Category> list = categoryService.getAll();
        model.addAttribute("list_category", list);
        boolean check = false;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        if(cookies == null){
            return "web/user/home";
        }else{
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if(cookie.getName().equals(cookieName)){
                    check = true;
                    break;
                }
            }
            if(check == true){
                checkCookie = 1;
                model.addAttribute("checkCookie", checkCookie);
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
