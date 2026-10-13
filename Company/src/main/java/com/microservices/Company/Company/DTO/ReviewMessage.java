package com.microservices.Company.Company.DTO;

import lombok.Data;

@Data
public class ReviewMessage {

    private Long id;

    private String title;

    private String review;

    private Double rating;

    private Long companyId;
}
