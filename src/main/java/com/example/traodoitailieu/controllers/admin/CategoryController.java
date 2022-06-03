package com.example.traodoitailieu.controllers.admin;

import com.example.traodoitailieu.entities.Category;
import com.example.traodoitailieu.entities.User;
import com.example.traodoitailieu.services.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public String list(Model model){
        ArrayList<Category> list = categoryService.getAll();
        model.addAttribute("list", list);
        return "admin/category/list";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("obj", new Category());
        return "admin/category/add";
    }

    @PostMapping("/do-add")
    public String doAdd(Category category){
        categoryService.saveCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id){
        model.addAttribute("obj", categoryService.getById(id));
        return "admin/category/edit";
    }

    @PostMapping("/do-edit")
    public String doEdit(Category category){
        categoryService.saveCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        categoryService.deleteById(id);
        return "redirect:/admin/category";
    }
}
