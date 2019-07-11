package com.trongdung.website.controller.ClientController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/homePage")
    public String homePage() {
        return "client/index";
    }

    @GetMapping(value = "/")
    public String index() {
        return "redirect:/homePage";
    }

    @GetMapping(value = "/client")
    public String client() {
        return "client/client";
    }
}
