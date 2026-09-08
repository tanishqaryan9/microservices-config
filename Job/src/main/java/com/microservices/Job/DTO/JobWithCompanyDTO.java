package com.microservices.Job.DTO;

import com.microservices.Job.External.Company;
import com.microservices.Job.External.Reviews;
import com.microservices.Job.Job.Job;
import lombok.Data;

import java.util.List;

@Data
public class JobWithCompanyDTO {

    private Long id;

    private String title;

    private String description;

    private String minSalary;

    private String maxSalary;

    private String location;

    private Company company;

    private List<Reviews> reviews;
}
