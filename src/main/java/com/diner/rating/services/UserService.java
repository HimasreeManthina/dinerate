package com.diner.rating.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.diner.rating.dto.ReviewDto;
import com.diner.rating.dto.UserProfileDto;
import com.diner.rating.entities.User;
import com.diner.rating.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;


    public UserProfileDto getUserProfile(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        List<ReviewDto> reviewDtos = user.getReviews().stream().map(r -> {
            ReviewDto dto = new ReviewDto();
            dto.setComment(r.getComment());
            dto.setRating(r.getRating());
            dto.setUserId(r.getUser().getId());
            dto.setRestaurantId(r.getRestaurant().getId());
            return dto;
        }).collect(Collectors.toList());

        return new UserProfileDto(user.getName(), user.getEmail(), reviewDtos);
    }
}
