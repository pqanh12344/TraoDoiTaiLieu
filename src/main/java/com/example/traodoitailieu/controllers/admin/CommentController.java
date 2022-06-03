package com.example.traodoitailieu.controllers.admin;

import com.example.traodoitailieu.entities.Category;
import com.example.traodoitailieu.entities.Comment;
import com.example.traodoitailieu.services.impl.CategoryService;
import com.example.traodoitailieu.services.impl.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("")
    public String list(Model model){
        ArrayList<Comment> list = commentService.getAll();
        model.addAttribute("list", list);
        return "admin/comment/list";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("obj", new Comment());
        return "admin/comment/add";
    }

    @PostMapping("/do-add")
    public String doAdd(Comment comment){
        commentService.saveComment(comment);
        return "redirect:/admin/comment";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id){
        model.addAttribute("obj", commentService.getById(id));
        return "admin/comment/edit";
    }

    @PostMapping("/do-edit")
    public String doEdit(Comment comment){
        commentService.saveComment(comment);
        return "redirect:/admin/comment";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        commentService.deleteById(id);
        return "redirect:/admin/comment";
    }
}
