package com.microservices.Reviews.Reviews;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ReviewDTO> getAllReviews(Long companyId) {
        List<Reviews> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews.stream().map(elements-> modelMapper.map(elements,ReviewDTO.class)).toList();
    }

    @Override
    public ReviewDTO getReviewById(Long reviewId) {
        Reviews review=reviewRepository.findById(reviewId).orElseThrow(()->new IllegalArgumentException("Review with id: "+reviewId+" not found."));
        return modelMapper.map(review,ReviewDTO.class);
    }

    @Override
    public ReviewDTO postReview(Long companyId, ReviewDTO dto) {
        Reviews reviews=modelMapper.map(dto, Reviews.class);
        reviews.setCompanyId(companyId);
        reviewRepository.save(reviews);
        return modelMapper.map(reviews, ReviewDTO.class);
    }

    @Override
    public ReviewDTO updateReview(Long reviewId, ReviewDTO dto) {
        Reviews review=reviewRepository.findById(reviewId).orElseThrow(()->new IllegalArgumentException("Review with id: "+reviewId+" not found."));
        dto.setId(reviewId);
        modelMapper.map(dto, review);
        reviewRepository.save(review);
        return modelMapper.map(review, ReviewDTO.class);
    }

    @Override
    public void deleteReview(Long reviewId) {
        Reviews review=reviewRepository.findById(reviewId).orElseThrow(()->new IllegalArgumentException("Review with id: "+reviewId+" not found."));
        reviewRepository.delete(review);
    }
}
