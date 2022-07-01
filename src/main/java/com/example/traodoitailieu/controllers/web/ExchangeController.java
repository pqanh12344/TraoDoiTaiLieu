package com.example.traodoitailieu.controllers.web;

import com.example.traodoitailieu.entities.Category;
import com.example.traodoitailieu.entities.Comment;
import com.example.traodoitailieu.entities.Post;
import com.example.traodoitailieu.entities.User;
import com.example.traodoitailieu.services.impl.CategoryService;
import com.example.traodoitailieu.services.impl.CommentService;
import com.example.traodoitailieu.services.impl.PostService;
import com.example.traodoitailieu.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
public class ExchangeController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @GetMapping("/exchange")
    public String Exchange(Model model, HttpServletRequest request){
        model.addAttribute("post", new Post());
        model.addAttribute("comment", new Comment());
        int checkCookie = 0;
        ArrayList<Category> list = categoryService.getAll();
        model.addAttribute("list_category", list);
        ArrayList<Post> listPost = postService.getAll();
        ArrayList<Post> listPost12 = new ArrayList<>();
        for (int i = listPost.size()-1; i >= 0; i--){
            listPost12.add(listPost.get(i));
        }
        model.addAttribute("list_post", listPost12);
        ArrayList<User> listUser = userService.getAll();
        model.addAttribute("list_user", listUser);
        ArrayList<Comment> listComment = commentService.getAll();
        model.addAttribute("list_comment", listComment);
        boolean check = false;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        if(cookies == null){
            return "web/comment/exchange";
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
                return "web/comment/exchange";
            }
        }
        return "web/comment/exchange";
    }

    @PostMapping("/do-post")
    public String doAdd(Post post, HttpServletRequest request){
        int k = 0;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if(cookie.getName().equals(cookieName)){
                k = Integer.parseInt(cookie.getValue());
                break;
            }
        }
        post.setUser_id(k);
        Calendar cal = Calendar.getInstance();
        Date b = cal.getTime();
        post.setUpdated_up(b);
        postService.savePost(post);
        return "redirect:/exchange";
    }

    @PostMapping("/do-comment")
    public String doAddComment(Comment comment, Post post, HttpServletRequest request, @RequestParam("id") int id){
        int k = 0;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if(cookie.getName().equals(cookieName)){
                k = Integer.parseInt(cookie.getValue());
                break;
            }
        }
        int post_id = Integer.parseInt(request.getParameter("name1"));
        comment.setUser_id(k);
        comment.setPost_id(post_id);
        Calendar cal = Calendar.getInstance();
        Date b = cal.getTime();
        comment.setUpdated_up(b);
        commentService.saveComment(comment);
        return "redirect:/doReply?id=" + id;
    }

    @GetMapping("/doReply")
    public String doReplyGet(Model model, Comment comment, Post post, HttpServletRequest request, @RequestParam("id") int id){
        Post posttl = postService.getById(id);
        model.addAttribute("item", posttl);
        int checkCookie = 0;
        ArrayList<Category> list = categoryService.getAll();
        model.addAttribute("list_category", list);
        ArrayList<Post> listPost = postService.getAll();
        model.addAttribute("list_post", listPost);
        ArrayList<User> listUser = userService.getAll();
        model.addAttribute("list_user", listUser);
        ArrayList<Comment> listComment = commentService.getAll();
        ArrayList<Comment> listComment12 = new ArrayList<>();
        for (int i = listComment.size()-1; i >= 0; i--){
            listComment12.add(listComment.get(i));
        }
        model.addAttribute("list_comment", listComment12);
        boolean check = false;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        if(cookies == null){
            return "web/comment/reply";
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
                return "web/comment/reply";
            }
        }
        return "web/comment/reply";
    }

    @PostMapping("/do-reply")
    public String doReply(Model model, Comment comment, Post post, HttpServletRequest request, @RequestParam("id") int id){
        Post posttl = postService.getById(id);
        model.addAttribute("item", posttl);
        int checkCookie = 0;
        ArrayList<Category> list = categoryService.getAll();
        model.addAttribute("list_category", list);
        ArrayList<Post> listPost = postService.getAll();
        model.addAttribute("list_post", listPost);
        ArrayList<User> listUser = userService.getAll();
        model.addAttribute("list_user", listUser);
        ArrayList<Comment> listComment = commentService.getAll();
        ArrayList<Comment> listComment12 = new ArrayList<>();
        for (int i = listComment.size()-1; i >= 0; i--){
            listComment12.add(listComment.get(i));
        }
        model.addAttribute("list_comment", listComment12);
        boolean check = false;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        if(cookies == null){
            return "web/comment/reply";
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
                return "web/comment/reply";
            }
        }
        return "web/comment/reply";
    }
}
