package com.uploadphoto.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Users
{
    @Id
    private Long id;
    private String name;
    private Byte[] image;

}
