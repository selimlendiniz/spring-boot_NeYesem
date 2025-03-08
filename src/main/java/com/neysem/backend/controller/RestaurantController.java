package com.neysem.backend.controller;

import com.neysem.backend.dto.SaveRestaurantRequest;
import com.neysem.backend.model.Restaurant;
import com.neysem.backend.repo.RestaurantRepository;
import com.neysem.backend.service.RestaurantService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public Restaurant saveRestaurant(@RequestBody SaveRestaurantRequest request) {
        return restaurantService.saveRestaurant(request);
    }
}
