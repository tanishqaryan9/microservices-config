package com.microservices.Company.Company.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "REVIEWS", url = "${review-service.url}")
public interface ReviewClient {

    @GetMapping("/reviews/averageRating")
    Double getAverageRating(@RequestParam Long companyId);
}
