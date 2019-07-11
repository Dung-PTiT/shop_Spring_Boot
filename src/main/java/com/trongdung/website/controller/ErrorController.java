package com.trongdung.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping(value = "/access-denied")
    public String accessDenied(){
        return "error/accessDenied";
    }
}
