package com.diner.rating.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.diner.rating.dto.UserProfileDto;
import com.diner.rating.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}/profile")
    public UserProfileDto getUserProfile(@PathVariable Long id) {
        return userService.getUserProfile(id);
    }

    public UserService getUserService() {
        return userService;
    }
}
