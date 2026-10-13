package com.microservices.Company.Company.messaging;

import com.microservices.Company.Company.CompanyService;
import com.microservices.Company.Company.DTO.ReviewMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewMessageConsumer {

    private final CompanyService companyService;

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage)
    {
        companyService.updateCompanyRating(reviewMessage);
    }
}
