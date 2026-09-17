package com.wu0089.websit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class ProductsController {
    @GetMapping ("/products")
    public String products(){
        return "coming-soon";
    }
    
}
