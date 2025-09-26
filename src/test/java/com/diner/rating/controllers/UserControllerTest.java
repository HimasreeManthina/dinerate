package com.diner.rating.controllers;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.diner.rating.dto.ReviewDto;
import com.diner.rating.dto.UserProfileDto;
import com.diner.rating.services.UserService;

public class UserControllerTest {
    private UserService userService;
    private UserController userController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
        userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getUserProfile_shouldReturnUserProfileDto() throws Exception {
        ReviewDto review = new ReviewDto(1L, 5L,3, "Great food!");
        
        UserProfileDto dto = new UserProfileDto("Naveena", "P", List.of(review));
        when(userService.getUserProfile(1L)).thenReturn(dto);

        mockMvc.perform(get("/user/1/profile")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getUserProfile_shouldCallServiceWithCorrectId() {
        UserProfileDto dto = new UserProfileDto();
        when(userService.getUserProfile(42L)).thenReturn(dto);

        UserProfileDto result = userController.getUserProfile(42L);

        assertEquals(dto, result);
        Mockito.verify(userService).getUserProfile(42L);
    }

    @Test
    void getUserService_shouldReturnInjectedService() {
        assertSame(userService, userController.getUserService());
    }
}
