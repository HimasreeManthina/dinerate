package com.diner.rating.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diner.rating.entities.Restaurant;
import com.diner.rating.services.RestaurantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(@RequestParam Optional<String> city,
                                           @RequestParam Optional<String> cuisine) {
        return restaurantService.filterRestaurants(city, cuisine);
    }
}