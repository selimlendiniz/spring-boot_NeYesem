package com.neysem.backend.controller;

import com.neysem.backend.dto.GetRestaurantProfileResponse;
import com.neysem.backend.dto.SaveRestaurantRequest;
import com.neysem.backend.dto.SaveRestaurantResponse;
import com.neysem.backend.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public SaveRestaurantResponse saveRestaurant(@RequestBody SaveRestaurantRequest request) {
        return restaurantService.saveRestaurant(request);
    }

    @GetMapping("/{restaurantId}")
    public GetRestaurantProfileResponse getRestaurantProfile(@PathVariable("restaurantId") Long restaurantId) {
        System.out.println("restaurantId: " + restaurantId);
        return restaurantService.getRestaurantProfile(restaurantId);
    }
}
