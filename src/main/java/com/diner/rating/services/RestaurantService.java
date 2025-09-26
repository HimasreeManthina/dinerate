package com.diner.rating.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.diner.rating.entities.Restaurant;
import com.diner.rating.repositories.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepo;


    public List<Restaurant> filterRestaurants(Optional<String> city, Optional<String> cuisine) {
        return restaurantRepo.findByCityContainingIgnoreCaseAndCuisineContainingIgnoreCase(
            city.orElse(""), cuisine.orElse("")
        );
    }

     }

