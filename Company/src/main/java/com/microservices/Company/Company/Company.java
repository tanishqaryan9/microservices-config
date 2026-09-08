package com.microservices.Company.Company;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private List<Integer> jobs=new ArrayList<>();

    private List<Integer> reviews=new ArrayList<>();
}
