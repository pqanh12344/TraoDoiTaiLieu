package com.example.traodoitailieu.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryWebController {

    @GetMapping("/category")
    public String Detail(Model model, @RequestParam("id") int id){
        return "index";
    }
}
