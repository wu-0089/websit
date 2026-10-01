package com.wu0089.websit.service;



import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

        public Map<String, List<Products>> findAllByCaregory(){
            return productRepos.findAll().stream()
                                .collect(Collectors.groupingBy(
                                    Products::getCategory,
                                    LinkedHashMap::new,
                                    Collectors.toList()
                                ));
        }
    }