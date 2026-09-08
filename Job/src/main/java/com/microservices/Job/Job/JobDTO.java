package com.microservices.Job.Job;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonPropertyOrder({
        "id",
        "title",
        "description",
        "minSalary",
        "maxSalary",
        "location"
})
@Data
public class JobDTO {

    private Long id;

    private String title;

    private String description;

    private String minSalary;

    private String maxSalary;

    private String location;

    private Long companyId;

    private Long reviewId;
}
