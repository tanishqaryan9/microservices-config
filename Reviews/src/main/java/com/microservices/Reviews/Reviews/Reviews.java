package com.microservices.Reviews.Reviews;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String review;

    private double rating;

    private Long companyId;
}
