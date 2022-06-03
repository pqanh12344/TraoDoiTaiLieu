package com.example.traodoitailieu.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CommonController {

    @GetMapping("/admin")
    public String list(Model model){
        return "admin/admin-common";
    }
}
