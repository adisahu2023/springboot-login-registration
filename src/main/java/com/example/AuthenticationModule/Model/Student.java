package com.example.AuthenticationModule.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Student {
    @Id
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String address;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image;
}
