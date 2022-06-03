package com.example.traodoitailieu;

import com.example.traodoitailieu.entities.Document;
import com.example.traodoitailieu.services.impl.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class Test {

    @Autowired
    DocumentService documentService;

    @GetMapping("/test")
    public String Test(Model model){
        model.addAttribute("a", "pdf");
        return "test";
    }
}
