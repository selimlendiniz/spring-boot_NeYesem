package com.neysem.backend.mapper;

import com.neysem.backend.dto.GetRestaurantProfileResponse;
import com.neysem.backend.dto.SaveCommentRequest;
import com.neysem.backend.dto.SaveCommentResponse;
import com.neysem.backend.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "restaurant.name", target = "restaurantName")
    @Mapping(source = "customer.username", target = "customerUsername")
    @Mapping(source = "comment", target = "comment")
    @Mapping(source = "rating", target = "rating")
    SaveCommentResponse toDto(Comment comment);

    @Mapping(source = "customer.name", target = "customerName")
    @Mapping(source = "customer.surname", target = "customerSurname")
    @Mapping(source = "comment", target = "comment")
    @Mapping(source = "rating", target = "rating")
    GetRestaurantProfileResponse.CommentDto toCommentDto(Comment comment);
}
