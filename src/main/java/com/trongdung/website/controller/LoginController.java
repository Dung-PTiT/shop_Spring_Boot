package com.trongdung.website.controller;
import com.trongdung.website.model.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping(value = "/login")
    public String login() {
        return "admin/login/login";
    }
//    @GetMapping(value = "/admin/welcome")
//    public String welcome(Authentication authentication){
//        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
//        return "admin/user/list";
//    }
//
//    @GetMapping(value = "/admin/dashboard")
//    public String getUser(){
//        Authentication authentication = new SecurityContextHolder().getContext().getAuthentication();
//        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
//        return "admin/user/list";
//    }
}
