package com.library.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
    private Long id;

    @NotEmpty(message = "Name is required")
    @NotNull
    private String bookName;

    @NotEmpty(message = "Description is required")
    @NotNull
    private String bookDescription;

    @Min(value = 0, message = "Price >= 1")
    @NotNull(message = "Price cannot be null")
    private Integer bookPrice;

    @Min(value = 0, message = "Amount >= 1")
    @NotNull(message = "Amount cannot be null")
    private Integer bookAmount;

    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;
}
