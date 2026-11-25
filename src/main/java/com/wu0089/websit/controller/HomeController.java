package com.wu0089.websit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller 
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
     @GetMapping("/about")
    public String about() {
        return "coming-soon";
    }
     @GetMapping("/services")
    public String services() {
        return "coming-soon";
    }
}
