package com.wu0089.websit.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("productsByCategory", productService.findAllByCategory());
       // List <Products> products = productService.findAll();
       // model.addAttribute("products", products);
        return "products";
    }

   @PostMapping("/products")
    public String postMethodName(
            Products products,
            @RequestParam(
                    value = "imageFile",
                    required = false
            ) MultipartFile imageFile
    ) throws IOException {

        swith:
        // 有選圖片
        if (imageFile != null && !imageFile.isEmpty()) {

            // 圖片轉 byte[]
            byte[] imageBytes = imageFile.getBytes();

            // byte[] 轉 Base64
            String imageBase64 =
                    Base64.getEncoder()
                          .encodeToString(imageBytes);

            // 取得圖片格式
            String imageType =
                    imageFile.getContentType();

            // 存進 Products
            products.setImageBase64(imageBase64);
            products.setImageType(imageType);
        }


        // 新增
        productService.add(products);


        return "redirect:/products?action=edit";
    }

}