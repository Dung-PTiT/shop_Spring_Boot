package com.trongdung.website.controller.AdminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class OrderController {
    @GetMapping(value = "/orderList")
    public String orderList(){
        return null;
    }
}
