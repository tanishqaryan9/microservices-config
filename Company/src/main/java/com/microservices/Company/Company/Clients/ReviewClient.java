package com.microservices.Company.Company.External;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("REVIEWS")
public class ReviewClient {
    
}
