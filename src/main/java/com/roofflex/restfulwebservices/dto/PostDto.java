package com.roofflex.restfulwebservices.dto;

import com.roofflex.restfulwebservices.model.Post;
import jakarta.validation.constraints.Size;

/**
 * Dto for {@link Post} model
 *
 * @param description post description
 */
public record PostDto(
        @Size(min = 10, message = "Description should be at least 10 characters length") String description) {
}
