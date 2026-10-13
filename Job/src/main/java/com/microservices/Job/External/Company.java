package com.microservices.Job.External;

import lombok.Data;

@Data
public class Company {

    private Long id;

    private String name;

    private Double avgRating;

    private String description;
}
