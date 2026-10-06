package com.wu0089.websit.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "products")     
public class Products {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String  name;
    private String  category;//種類
    private String  description;
    private Integer price;
    private Integer stock;
    private LocalDateTime createtime;

    @Lob
    @Column (name = "image_base64", columnDefinition = "LONGTEXT")
    private String imageBase64;
    private String imageType;
}
