package com.wu0089.websit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wu0089.websit.entity.Products;

public interface ProductRepos extends JpaRepository<Products, Integer>{
    
}
