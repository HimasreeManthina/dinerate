package com.diner.rating.repositories;
import com.diner.rating.entities.Review;
import com.diner.rating.entities.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRestaurant(Restaurant restaurant);
    Page<Review> findByRestaurantId(Long restaurantId, Pageable pageable);
    boolean existsByUserIdAndRestaurantId(Long userId, Long restaurantId);
}