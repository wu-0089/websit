package com.wu0089.websit.service;



import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wu0089.websit.entity.Products;
import com.wu0089.websit.repository.ProductRepos;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Service 
public class ProductService {
        private final ProductRepos productRepos;

        public void add(Products products, MultipartFile imageFile) throws IOException{
        // 有選圖片
        if (imageFile != null && !imageFile.isEmpty()) {
            // 圖片轉 byte[]
            byte[] imageBytes = imageFile.getBytes();
            // byte[] 轉 Base64
            String imageBase64 = Base64.getEncoder()
                                        .encodeToString(imageBytes);
            // 取得圖片格式
            String imageType = imageFile.getContentType();
            // 存進 Products
            products.setImageBase64(imageBase64);
            products.setImageType(imageType);
        }
            products.setCreatetime(LocalDateTime.now());
            
            productRepos.save(products);
        } 

        //public void update()

        public void delete(Integer id){
            productRepos.deleteById(id);
        }


        public List <Products> findAll(){
           return productRepos.findAll();
        }


        public Map<String, List<Products>> findAllByCategory(){
            return productRepos.findAll().stream()
                                .collect(Collectors.groupingBy(
                                    Products::getCategory,
                                    LinkedHashMap::new,
                                    Collectors.toList()
                                ));
        }
    }