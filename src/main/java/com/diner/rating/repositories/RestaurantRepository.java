package com.diner.rating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diner.rating.entities.Restaurant;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByCityContainingIgnoreCaseAndCuisineContainingIgnoreCase(String city, String cuisine);
}