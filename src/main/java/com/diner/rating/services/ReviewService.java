package com.diner.rating.services;

import org.springframework.data.domain.Pageable;
import java.util.List;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import com.diner.rating.entities.Review;

import lombok.RequiredArgsConstructor;
import com.diner.rating.entities.Restaurant;
import com.diner.rating.entities.User;
import com.diner.rating.repositories.ReviewRepository;
import com.diner.rating.repositories.RestaurantRepository;
import com.diner.rating.repositories.UserRepository;
import com.diner.rating.dto.ReviewDto;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepo;
    private final RestaurantRepository restaurantRepo;
    private final UserRepository userRepo;

    public void submitReview(ReviewDto dto) {
        if (reviewRepo.existsByUserIdAndRestaurantId(dto.getUserId(), dto.getRestaurantId())) {
            throw new RuntimeException("User already reviewed this restaurant.");
        }

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Restaurant restaurant = restaurantRepo.findById(dto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Review review = new Review();
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setTimestamp(LocalDateTime.now());
        review.setRestaurant(restaurant);
        review.setUser(user);

        reviewRepo.save(review);
        recalculateAverageRating(restaurant);
    }

    private void recalculateAverageRating(Restaurant restaurant) {
        List<Review> reviews = reviewRepo.findByRestaurant(restaurant);
        double avg = reviews.stream().mapToInt(Review::getRating).average().orElse(0);
        restaurant.setAverageRating(avg);
        restaurantRepo.save(restaurant);
    }

    public Page<Review> getReviewsByRestaurant(Long restaurantId, Pageable pageable) {
        return reviewRepo.findByRestaurantId(restaurantId, pageable);
    }
}
