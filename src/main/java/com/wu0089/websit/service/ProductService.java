package com.wu0089.websit.service;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wu0089.websit.entity.Products;
import com.wu0089.websit.repository.ProductRepos;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Service 
public class ProductService {
        private final ProductRepos productRepos;

        public void add(Products products){
            
            products.setCreatetime(LocalDateTime.now());
            
            productRepos.save(products);
        }

        public List <Products> findAll(){
            return productRepos.findAll();
        }
    }