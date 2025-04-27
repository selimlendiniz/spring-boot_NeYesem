package com.neysem.backend.mapper;

import com.neysem.backend.dto.GetAllRestaurantByNameResponse;
import com.neysem.backend.dto.GetRestaurantProfileResponse;
import com.neysem.backend.dto.SaveRestaurantResponse;
import com.neysem.backend.model.Restaurant;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CommentMapper.class})
public interface RestaurantMapper {
    @Mapping(source = "managerEmail", target = "manager.email")
    @Mapping(source = "managerUsername", target = "manager.username")
    @Mapping(source = "managerSurname", target = "manager.surname")
    @Mapping(source = "managerName", target = "manager.name")
    Restaurant toEntity(SaveRestaurantResponse saveRestaurantResponse);

    @InheritInverseConfiguration(name = "toEntity")
    SaveRestaurantResponse toDto(Restaurant restaurant);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Restaurant partialUpdate(SaveRestaurantResponse saveRestaurantResponse, @MappingTarget Restaurant restaurant);

    @Mapping(source = "managerEmail", target = "manager.email")
    @Mapping(source = "managerSurname", target = "manager.surname")
    @Mapping(source = "managerName", target = "manager.name")
    Restaurant toEntity(GetRestaurantProfileResponse getRestaurantProfileResponse);

    @AfterMapping
    default void linkComment(@MappingTarget Restaurant restaurant) {
        restaurant.getComment().forEach(comment -> comment.setRestaurant(restaurant));
    }

    @InheritInverseConfiguration(name = "toEntity")
    GetRestaurantProfileResponse toDto1(Restaurant restaurant);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Restaurant partialUpdate(GetRestaurantProfileResponse getRestaurantProfileResponse, @MappingTarget Restaurant restaurant);

    Restaurant toEntity(GetAllRestaurantByNameResponse getAllRestaurantByNameResponse);

    List<GetAllRestaurantByNameResponse> toDto2(List<Restaurant> restaurant);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Restaurant partialUpdate(GetAllRestaurantByNameResponse getAllRestaurantByNameResponse, @MappingTarget Restaurant restaurant);
}