package com.example.traodoitailieu.controllers.admin;

import com.example.traodoitailieu.entities.User;
import com.example.traodoitailieu.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public String list(Model model){
        ArrayList<User> list = userService.getAll();
        model.addAttribute("list", list);
        return "admin/user/list";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("obj", new User());
        return "admin/user/add";
    }

    @PostMapping("/do-add")
    public String doAdd(User user){
        userService.saveUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id){
        model.addAttribute("obj", userService.getById(id));
        return "admin/user/edit";
    }

    @PostMapping("/do-edit")
    public String doEdit(User user){
        userService.saveUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        userService.deleteById(id);
        return "redirect:/admin/user";
    }
}
