package com.example.traodoitailieu.controllers.admin;

import com.example.traodoitailieu.entities.Admin;
import com.example.traodoitailieu.entities.User;
import com.example.traodoitailieu.services.impl.AdminService;
import com.example.traodoitailieu.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("")
    public String list(Model model){
        ArrayList<Admin> list = adminService.getAll();
        model.addAttribute("list", list);
        return "admin/admin/list";
    }
}
