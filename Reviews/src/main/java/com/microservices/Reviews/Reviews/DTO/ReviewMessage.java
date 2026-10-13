package com.microservices.Reviews.Reviews.DTO;

import lombok.Data;

@Data
public class ReviewMessage {

    private Long id;

    private String title;

    private String review;

    private double rating;

    private Long companyId;
}
