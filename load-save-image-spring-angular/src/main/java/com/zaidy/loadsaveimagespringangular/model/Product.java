package com.zaidy.loadsaveimagespringangular.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity @AllArgsConstructor @NoArgsConstructor @Data
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    @Column(name = "image", unique = false, nullable = false, length = 100000)
    private byte[] image;

}
