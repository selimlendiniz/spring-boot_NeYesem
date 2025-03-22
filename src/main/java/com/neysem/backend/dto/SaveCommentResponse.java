package com.neysem.backend.dto;

import com.neysem.backend.model.Comment;

import java.io.Serializable;

/**
 * DTO for {@link Comment}
 */
public record SaveCommentResponse(
        String restaurantName,
        String customerUsername,
        String comment
) implements Serializable {
  }