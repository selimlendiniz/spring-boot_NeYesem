package com.neysem.backend.dto;

import com.neysem.backend.model.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link Comment}
 */
public record SaveCommentRequest(
        Long restaurantId,
        @NotNull @NotEmpty @NotBlank String comment
) implements Serializable {
}