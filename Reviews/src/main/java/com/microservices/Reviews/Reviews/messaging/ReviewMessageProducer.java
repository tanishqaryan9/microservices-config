package com.microservices.Reviews.Reviews.messaging;

import com.microservices.Reviews.Reviews.DTO.ReviewMessage;
import com.microservices.Reviews.Reviews.ReviewDTO;
import com.microservices.Reviews.Reviews.Reviews;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(ReviewDTO reviews)
    {
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setId(reviews.getId());
        reviewMessage.setTitle(reviews.getTitle());
        reviewMessage.setReview(reviews.getReview());
        reviewMessage.setRating(reviews.getRating());
        reviewMessage.setCompanyId(reviews.getCompanyId());

        rabbitTemplate.convertAndSend("companyRatingQueue",reviewMessage);
    }
}
