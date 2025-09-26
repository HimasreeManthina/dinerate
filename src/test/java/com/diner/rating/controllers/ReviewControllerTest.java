package com.diner.rating.controllers;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.diner.rating.dto.ReviewDto;
import com.diner.rating.entities.Review;
import com.diner.rating.services.ReviewService;
public class ReviewControllerTest {

    private ReviewService reviewService;
    private ReviewController reviewController;

    @BeforeEach
    void setUp() {
        reviewService = mock(ReviewService.class);
        // Use reflection to inject the mock since the field is final and initialized as null
        reviewController = new ReviewController(reviewService);
    }

    @Test
    void submitReview_ShouldCallServiceAndReturnCreated() {
        ReviewDto dto = new ReviewDto();
        doNothing().when(reviewService).submitReview(dto);

        ResponseEntity<Void> response = reviewController.submitReview(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(reviewService, times(1)).submitReview(dto);
    }

    @Test
    void getReviews_ShouldReturnPageOfReviews() {
        Long restaurantId = 1L;
        Pageable pageable = Pageable.unpaged();
        Review review = new Review();
        Page<Review> reviewPage = new PageImpl<>(Collections.singletonList(review));
        when(reviewService.getReviewsByRestaurant(restaurantId, pageable)).thenReturn(reviewPage);

        Page<Review> result = reviewController.getReviews(restaurantId, pageable);

        assertEquals(1, result.getTotalElements());
        verify(reviewService, times(1)).getReviewsByRestaurant(restaurantId, pageable);
    }
}
