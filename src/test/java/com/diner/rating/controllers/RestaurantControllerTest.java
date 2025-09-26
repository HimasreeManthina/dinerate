package com.diner.rating.controllers;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.diner.rating.entities.Restaurant;
import com.diner.rating.services.RestaurantService;


public class RestaurantControllerTest {


        private MockMvc mockMvc;
        private RestaurantService restaurantService;

        @BeforeEach
        void setUp() {
            restaurantService = mock(RestaurantService.class);
            RestaurantController controller = new RestaurantController(restaurantService);
            mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        }

        @Test
        void getRestaurants_returnsListOfRestaurants() throws Exception {
            Restaurant r1 = new Restaurant();
            r1.setId(1L);
            r1.setName("Testaurant");
            Restaurant r2 = new Restaurant();
            r2.setId(2L);
            r2.setName("Food Place");

            when(restaurantService.filterRestaurants(Optional.empty(), Optional.empty()))
                    .thenReturn(Arrays.asList(r1, r2));

            mockMvc.perform(get("/restaurants")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].name").value("Testaurant"))
                    .andExpect(jsonPath("$[1].name").value("Food Place"));
        }

        @Test
        void getRestaurants_withCityAndCuisineParams() throws Exception {
            Restaurant r = new Restaurant();
            r.setId(3L);
            r.setName("City Cuisine");

            when(restaurantService.filterRestaurants(Optional.of("New York"), Optional.of("Italian")))
                    .thenReturn(Collections.singletonList(r));

            mockMvc.perform(get("/restaurants")
                            .param("city", "New York")
                            .param("cuisine", "Italian")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].name").value("City Cuisine"));
        }

        @Test
        void getRestaurants_returnsEmptyListWhenNoRestaurantsFound() throws Exception {
            when(restaurantService.filterRestaurants(Optional.empty(), Optional.empty()))
                    .thenReturn(Collections.emptyList());

            mockMvc.perform(get("/restaurants")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json("[]"));
        }
    }

