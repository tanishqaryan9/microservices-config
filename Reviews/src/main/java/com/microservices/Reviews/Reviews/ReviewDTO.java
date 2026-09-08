package com.microservices.Reviews.Reviews;

import lombok.Data;

@Data
public class ReviewDTO {

    private Long id;

    private String title;

    private String review;

    private double rating;

    private Long companyId;

}
