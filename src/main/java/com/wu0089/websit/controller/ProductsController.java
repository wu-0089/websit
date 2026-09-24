package com.wu0089.websit.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wu0089.websit.entity.Products;
import com.wu0089.websit.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


/*
    products?action=edit  新增
    products?action=list 目錄
    products?action=updata  修改
    
 */


@RequiredArgsConstructor 
@Controller 
public class ProductsController {

    private final ProductService productService;
    
    @GetMapping ("/products")
    public String products(
        @RequestParam(defaultValue = "list") String action,
        @RequestParam(required = false) Integer id,
        Model model) {

        switch (action) {

            case "edit"  :
                return showaddPage(model);
            
            case "list" :
                return showPage(model);
            default:
                return showPage(model);
        }
        
   
    }

    private String showaddPage(Model model){
        model.addAttribute("product", new Products());
        model.addAttribute("Products", productService.findAll());
        return "product-edit";
    }

    private String showPage(Model model){
        List <Products> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/products")
    public String postMethodName(Products products) {
        
        productService.add(products);
        return "redirect:/products?action=list";
    }
    

    
}
