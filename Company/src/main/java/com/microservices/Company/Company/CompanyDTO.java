package com.microservices.Company.Company;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "jobs"
})
@Data
public class CompanyDTO {

    private Long id;

    private String name;

    private String description;

    private List<Integer> jobs;

    private List<Integer> reviews;
}
