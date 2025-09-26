package com.diner.rating.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.diner.rating.dto.ReviewDto;
import com.diner.rating.entities.Review;
import com.diner.rating.services.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService ;

    @PostMapping("/review")
    public ResponseEntity<Void> submitReview(@RequestBody @Valid ReviewDto dto) {
        reviewService.submitReview(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/restaurant/{id}/reviews")
    public Page<Review> getReviews(@PathVariable Long id,
                                   @PageableDefault(size = 10, sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable) {
        return reviewService.getReviewsByRestaurant(id, pageable);
    }

}