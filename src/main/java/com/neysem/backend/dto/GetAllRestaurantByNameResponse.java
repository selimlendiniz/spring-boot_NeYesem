package com.neysem.backend.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.neysem.backend.model.Restaurant}
 */
public record GetAllRestaurantByNameResponse(Long id, String name) implements Serializable {
}