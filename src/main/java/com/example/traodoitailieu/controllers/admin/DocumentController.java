package com.example.traodoitailieu.controllers.admin;

import com.example.traodoitailieu.entities.*;
import com.example.traodoitailieu.services.impl.CategoryService;
import com.example.traodoitailieu.services.impl.DocumentService;
import com.example.traodoitailieu.services.impl.UserDocumentService;
import com.example.traodoitailieu.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Locale;

@Controller
@RequestMapping("/admin/document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @GetMapping("")
    public String get(Model model) {
        ArrayList<Document> docs = documentService.getFiles();
        model.addAttribute("list", docs);
        return "admin/document/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        documentService.deleteById(id);
        return "redirect:/admin/document";
    }
}
