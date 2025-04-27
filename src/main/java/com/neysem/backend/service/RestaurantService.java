package com.neysem.backend.service;

import com.neysem.backend.dto.GetAllRestaurantByNameResponse;
import com.neysem.backend.dto.GetRestaurantProfileResponse;
import com.neysem.backend.dto.SaveRestaurantRequest;
import com.neysem.backend.dto.SaveRestaurantResponse;
import com.neysem.backend.mapper.RestaurantMapper;
import com.neysem.backend.model.Manager;
import com.neysem.backend.model.Restaurant;
import com.neysem.backend.repo.ManagerRepository;
import com.neysem.backend.repo.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ManagerRepository managerRepository;
    private final RestaurantMapper restaurantMapper;

    @Transactional
    public SaveRestaurantResponse saveRestaurant(SaveRestaurantRequest request) {


        Manager manager = managerRepository.findByUsername(
                    SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new RuntimeException("Manager not found!"));


        Restaurant restaurant = Restaurant.builder()
                .manager(manager)
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();

        // İlişkiyi iki yönlü senkronize et
        restaurant.setManager(manager);
        manager.setRestaurant(restaurant);  // Burası eksikti!

        return restaurantMapper.toDto(restaurantRepository.save(restaurant));
    }

    public Restaurant getRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found!"));
    }

    public GetRestaurantProfileResponse getRestaurantProfile(Long restaurantId) {
        return restaurantMapper.toDto1(getRestaurant(restaurantId));
    }

    public List<GetAllRestaurantByNameResponse> getAllRestaurantByName(String name) {
        List<Restaurant> restaurant = restaurantRepository.findTop5ByNameContainingIgnoreCase(name);
        return restaurantMapper.toDto2(restaurant);
    }

}
