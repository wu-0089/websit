package com.wu0089.websit.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wu0089.websit.entity.Products;
import com.wu0089.websit.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


/*
    products?action=edit  新增
    products?action=list 目錄
    products?action=updata  修改
    
 */


@RequestMapping("/products")
@RequiredArgsConstructor 
@Controller 
public class ProductsController {

    private final ProductService productService;
    
    @GetMapping
    public String products(Model model) {
        model.addAttribute("productsByCategory", productService.findAllByCategory());
        return "products";
        }
        
    @GetMapping("/edit")
    public String productedit(@RequestParam (required = false)Integer editId,
                                Model model) {
        model.addAttribute("product", new Products());

        model.addAttribute("Products", productService.findAll());

        model.addAttribute("editId", editId);

        return "product-edit";
    }

   @PostMapping("/edit")
    public String postMethodName( String action,
                                 Products products,
                    @RequestParam(value = "imageFile",
                                required = false
                                 ) MultipartFile imageFile
    ) throws IOException {

        switch (action) {
            case "add" -> productService.add(products, imageFile); 
            case "delete" -> productService.delete(products.getId());
          //  case "update" -> 
                
    }
        return "redirect:/products/edit";
    }
}
