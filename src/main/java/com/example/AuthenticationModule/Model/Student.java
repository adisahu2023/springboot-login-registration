package com.example.AuthenticationModule.Model;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Student {
    @Id
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
}
