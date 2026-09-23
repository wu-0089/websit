package com.wu0089.websit.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wu0089.websit.entity.Products;
import com.wu0089.websit.service.ProductService;

import lombok.RequiredArgsConstructor;

/*
    products?action=add  新增
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

        List <Products> product =productService.findAll();

        
        return "products";
    }

    
}
