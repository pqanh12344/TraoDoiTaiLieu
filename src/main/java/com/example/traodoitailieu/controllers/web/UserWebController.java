package com.example.traodoitailieu.controllers.web;

import com.example.traodoitailieu.entities.Category;
import com.example.traodoitailieu.entities.Document;
import com.example.traodoitailieu.entities.User;
import com.example.traodoitailieu.entities.UserDocument;
import com.example.traodoitailieu.services.impl.CategoryService;
import com.example.traodoitailieu.services.impl.DocumentService;
import com.example.traodoitailieu.services.impl.UserDocumentService;
import com.example.traodoitailieu.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
public class UserWebController {

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    DocumentService documentService;

    @Autowired
    UserDocumentService userDocumentService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("obj", new User());
        return "web/user/register";
    }

    @PostMapping("/do-register")
    public String doRegister(User user, RedirectAttributes redirectAttributes){
        if(userService.saveUser(user)){
            return "redirect:/login";
        }
        redirectAttributes.addFlashAttribute("error", "Đăng ký thất bại");
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "web/user/login";
    }

    @PostMapping("/do-login")
    public String doLogin(RedirectAttributes redirectAttributes, @RequestParam("userName") String user_name,
                          @RequestParam("password") String password, HttpServletResponse response, Model model) {
        User user = userService.getUser(user_name, password);
        if(user != null){
            Cookie cookie = new Cookie("user_id", user.getUser_id()+"");
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            return "redirect:/home";
        }else{
            redirectAttributes.addFlashAttribute("msg", "Tên đăng nhập hoặc mật khẩu chưa chính xác");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("user_id", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/home";
    }

    @GetMapping("/user_information")
    public String information(Model model, HttpServletRequest request) {
        int checkCookie = 0;
        boolean check = true;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        if(cookies == null){
            return null;
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
                return "web/user/information";
            }
        }
        return null;
    }

    @GetMapping("/my-page")
    public String MyPage(Model model, HttpServletRequest request){
        Cookie[] cookiesTrc = request.getCookies();
        int user_id = 0;
        if(cookiesTrc != null) {
            for (int i = 0; i < cookiesTrc.length; i++) {
                Cookie cookie = cookiesTrc[i];
                if (cookie.getName().equals("user_id")) {
                    user_id = Integer.parseInt(cookie.getValue());
                    break;
                }
            }
        }
        ArrayList<Document> docs = documentService.getFiles();
        model.addAttribute("docs", docs);
        ArrayList<UserDocument> listUserDocument = userDocumentService.getAll();
        ArrayList<UserDocument> listUserDoc = new ArrayList<>();
        for (UserDocument userDoc: listUserDocument) {
            if(userDoc.getUser_id() == user_id){
                listUserDoc.add(userDoc);
            }
        }
        model.addAttribute("listUserDoc", listUserDoc);
        int checkCookie = 0;
        ArrayList<Category> list = categoryService.getAll();
        model.addAttribute("list_category", list);
        boolean check = false;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        if(cookies == null){
            return "web/user/my-page";
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
                return "web/user/my-page";
            }
        }
        return "web/user/my-page";
    }

    @GetMapping("/show-document")
    public String ShowDocument(Model model, HttpServletRequest request, @RequestParam("id") int id) {
        String m  = "", n = "", k = "", h = "";
        UserDocument a = userDocumentService.getById(id);
        ArrayList<User> b = userService.getAll();
        for (User item1 : b) {
            if(item1.getUser_id() == a.getUser_id()){
                k += item1.getEmail();
                m += item1.getName();
                break;
            }
        }
        ArrayList<Document> c = documentService.getFiles();
        for (Document item : c) {
            if(item.getDocument_id() == a.getDocument_id()){
                n += item.getDocument_name();
                h += item.getDescription();
                break;
            }
        }
        model.addAttribute("name", m);
        model.addAttribute("doc", n);
        model.addAttribute("email", k);
        model.addAttribute("description", h);
        int checkCookie = 0;
        ArrayList<Category> list = categoryService.getAll();
        model.addAttribute("list_category", list);
        boolean check = false;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        if(cookies == null){
            return "web/user/show-document";
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
                return "web/user/show-document";
            }
        }
        return "web/user/show-document";
    }
}
