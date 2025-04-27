package com.neysem.backend.controller;

import com.neysem.backend.dto.GetAllRestaurantByNameResponse;
import com.neysem.backend.dto.GetRestaurantProfileResponse;
import com.neysem.backend.dto.SaveRestaurantRequest;
import com.neysem.backend.dto.SaveRestaurantResponse;
import com.neysem.backend.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    //Search Restaurant By Name
    @GetMapping
    public List<GetAllRestaurantByNameResponse> getRestaurantByName(@RequestParam String name) {
        return restaurantService.getAllRestaurantByName(name);
    }

    @PostMapping
    public SaveRestaurantResponse saveRestaurant(@RequestBody SaveRestaurantRequest request) {
        return restaurantService.saveRestaurant(request);
    }

    @GetMapping("/{restaurantId}")
    public GetRestaurantProfileResponse getRestaurantProfile(@PathVariable("restaurantId") Long restaurantId) {
        return restaurantService.getRestaurantProfile(restaurantId);
    }
}
