package com.neysem.backend.service;

import com.neysem.backend.dto.SaveRestaurantRequest;
import com.neysem.backend.model.Manager;
import com.neysem.backend.model.Restaurant;
import com.neysem.backend.model.User;
import com.neysem.backend.repo.ManagerRepository;
import com.neysem.backend.repo.RestaurantRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ManagerRepository managerRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, ManagerRepository managerRepository) {
        this.restaurantRepository = restaurantRepository;
        this.managerRepository = managerRepository;
    }

    public Restaurant saveRestaurant(SaveRestaurantRequest request) {


        Manager manager = managerRepository.findByUsername(
                    SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new RuntimeException("Manager not found!"));


        Restaurant restaurant = Restaurant.builder()
                .manager(manager)
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();
        return restaurantRepository.save(restaurant);
    }

}
