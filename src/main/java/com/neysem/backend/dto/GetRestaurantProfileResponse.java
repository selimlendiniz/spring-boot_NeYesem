package com.neysem.backend.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.neysem.backend.model.Restaurant}
 */
public record GetRestaurantProfileResponse(Long id, String managerName, String managerSurname, String managerEmail, List<CommentDto> comment, String name, String address, String phone, String email, String description) implements Serializable {
    /**
     * DTO for {@link com.neysem.backend.model.Comment}
     */
    public record CommentDto(Long id, String customerName, String customerSurname, String comment) implements Serializable {
    }
}