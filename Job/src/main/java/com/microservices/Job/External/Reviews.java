package com.microservices.Job.External;

import lombok.Data;

@Data
public class Reviews {

    private Long id;

    private String title;

    private String review;

    private double rating;
}
