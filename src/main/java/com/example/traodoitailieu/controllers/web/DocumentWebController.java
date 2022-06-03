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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

@Controller
public class DocumentWebController {

    @Autowired
    DocumentService documentService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserDocumentService userDocumentService;

    @Autowired
    UserService userService;

    @GetMapping("/listDoc")
    public String get(Model model) {
        ArrayList<Document> docs = documentService.getFiles();
        model.addAttribute("docs", docs);
        return "admin/document/doc";
    }

    @GetMapping("/listDocument/category")
    public String getDoc(HttpSession httpSession, Model model, HttpServletRequest request , @RequestParam("id") String id, HttpServletResponse response) throws GeneralSecurityException, IOException {
        httpSession.setAttribute("category_id", id+"");
        String as = (String)httpSession.getAttribute("category_id");
        ArrayList<Document> docs = documentService.getFiles();
        model.addAttribute("docs", docs);
        ArrayList<UserDocument> listUserDocument = userDocumentService.getAll();
        ArrayList<UserDocument> listUserDoc = new ArrayList<>();
        for (UserDocument userDoc: listUserDocument) {
            if(userDoc.getCategory_id() == Integer.parseInt(as)){
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
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if(cookie.getName().equals(cookieName)){
                check = true;
                break;
            }
        }
        if(cookies == null){
            return "web/document/khoTaiLieu";
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
                return "web/document/khoTaiLieu";
            }
        }
        return "web/document/khoTaiLieu";
    }

    @GetMapping("/searchDocument")
    public String getSearchDoc(Model model, HttpServletRequest request) throws GeneralSecurityException, IOException {
        int checkCookie = 0;
        boolean check = false;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        if(cookies == null){
            return "web/document/search-document";
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
                return "web/document/search-document";
            }
        }
        return "web/document/search-document";
    }

    @GetMapping("/searchKq")
    public String getSearchKqDoc(Model model, HttpServletRequest request) throws GeneralSecurityException, IOException {
        ArrayList<Document> docs = documentService.getFiles();
        String option1 = request.getParameter("option1");
        String option2 = request.getParameter("option2");
        String option3 = request.getParameter("option3");
        ArrayList<Document> taiLieu = new ArrayList<>();
        if(option1 != null && option1.equals("pdf")){
            for (Document doc: docs) {
                if(doc.getDocument_name().toLowerCase().contains(".pdf")){
                    taiLieu.add(doc);
                }
            }
        }
        if(option2!= null && option2.equals("doc")){
            for (Document doc: docs) {
                if(doc.getDocument_name().toLowerCase().contains(".doc")){
                    taiLieu.add(doc);
                }
            }
        }
        if(option3 != null && option3.equals("ppt")){
            for (Document doc: docs) {
                if(doc.getDocument_name().toLowerCase().contains(".ppt")){
                    taiLieu.add(doc);
                }
            }
        }
        model.addAttribute("docs", taiLieu);
        int checkCookie = 0;
        boolean check = false;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        if(cookies == null){
            return "web/document/search-kq";
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
                return "web/document/search-kq";
            }
        }
        return "web/document/search-kq";
    }

    @GetMapping("/upl")
    public String Upl(Model model, HttpServletRequest request){
        int checkCookie = 0;
        ArrayList<Category> list = categoryService.getAll();
        model.addAttribute("list_category", list);
        boolean check = false;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        if(cookies == null){
            return "web/document/upl";
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
                return "web/document/upl";
            }
        }
        return "web/document/upl";
    }

    @GetMapping("/upl-gdri")
    public String UplGdri(Model model, HttpServletRequest request){
        model.addAttribute("obj", new Document());
        int checkCookie = 0;
        ArrayList<Category> list = categoryService.getAll();
        model.addAttribute("list_category", list);
        boolean check = false;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        if(cookies == null){
            return "web/document/upl-gdri";
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
                return "web/document/upl-gdri";
            }
        }
        return "web/document/upl-gdri";
    }

    @PostMapping("/uploadFiles")
    public String uploadFile(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("imagen_1") MultipartFile files, HttpServletRequest request, HttpSession httpSession) {
        String as = (String)httpSession.getAttribute("category_id");
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        int user_id = 0;
        if(cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals(cookieName)) {
                    user_id = Integer.parseInt(cookie.getValue());
                    break;
                }
            }
        }
        documentService.saveFile(files, name, description);
        ArrayList<Document> k = documentService.getFiles();
        UserDocument a = new UserDocument();
        a.setUser_id(user_id);
        a.setDocument_id(k.get(k.size()-1).getDocument_id());
        a.setCategory_id(Integer.parseInt(as));
        userDocumentService.saveUserDocument(a);
        return "redirect:/listDocument/category?id=" + as;
    }

    /*@PostMapping("/uploadFiles")
    public String uploadFile(@RequestParam("files") MultipartFile files) {
        documentService.saveFile(files);
        return "redirect:/listDoc";
    }

    @GetMapping("/downloadFile")
    public void downloadFile(@Param("id") int id, HttpServletResponse response) throws IOException {
        Document doc = documentService.getFileById(id);
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + doc.getDoc_name();
        response.setHeader(headerKey, headerValue);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(doc.getData());
        outputStream.close();
    }*/

    @GetMapping("/search")
    public String Search(Model model, @RequestParam("search") String name, HttpServletRequest request){
        int checkNull = 0;
        ArrayList<Document> taiLieu = new ArrayList<>();
        ArrayList<Document> docs = documentService.getFiles();
        ArrayList<UserDocument> listUserDocument = userDocumentService.getAll();
        ArrayList<User> users = userService.getAll();
        ArrayList<Integer> a = new ArrayList<>();
        if(!name.equals("")){
            for (Document doc: docs) {
                if(doc.getDocument_name().toLowerCase().contains(name.toLowerCase())){
                    taiLieu.add(doc);
                }
            }

            for (User user1: users){
                if(user1.getName().toLowerCase().contains(name.toLowerCase())){
                    for (UserDocument b: listUserDocument){
                        if(user1.getUser_id() == b.getUser_id()){
                            a.add(b.getDocument_id());
                        }
                    }
                }
            }

            for (int i = 0; i < a.size(); i++){
                for (Document doc: docs) {
                    if(doc.getDocument_id() == a.get(i)){
                        taiLieu.add(doc);
                    }
                }
            }
        }
        ArrayList<UserDocument> listU = new ArrayList<>();
        for (UserDocument it: listUserDocument) {
            for (Document it1: taiLieu) {
                if(it.getDocument_id() == it1.getDocument_id()){
                    listU.add(it);
                }
            }
        }
        if(listU.isEmpty()){
            checkNull = -1;
        }
        model.addAttribute("checkNull", checkNull);
        model.addAttribute("kq", "Kết quả tìm kiếm:");
        model.addAttribute("docs", listU);
        model.addAttribute("docu", docs);
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

    @GetMapping("/detail")
    public String Detail(Model model, HttpServletRequest request, @RequestParam("id") int id) {
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
            return "web/document/detail";
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
                return "web/document/detail";
            }
        }
        return "web/document/detail";
    }
}
