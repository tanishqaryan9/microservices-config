package com.microservices.Reviews.Reviews;


import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getAllReviews(Long companyId);

    ReviewDTO getReviewById(Long reviewId);

    ReviewDTO postReview(Long companyId, ReviewDTO dto);

    void deleteReview(Long reviewId);

    ReviewDTO updateReview(Long reviewId, ReviewDTO dto);
}
