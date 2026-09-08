package com.microservices.Job.Clients;

import com.microservices.Job.External.Reviews;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "reviews")
public interface ReviewClient {

    @GetMapping("/reviews")
    List<Reviews> getReviews(@RequestParam("companyId") Long companyId);
}
