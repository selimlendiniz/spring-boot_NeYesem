package com.neysem.backend.dto;

import com.neysem.backend.model.Comment;
import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * DTO for {@link Comment}
 */
public record SaveCommentRequest(
        Long restaurantId,
        @NotNull @NotEmpty @NotBlank String comment,
        @Min(1) @Max(5) Double rating
) implements Serializable {
}