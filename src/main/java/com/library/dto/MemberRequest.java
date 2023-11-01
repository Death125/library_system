package com.library.dto;

import java.time.LocalDateTime;

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
public class MemberRequest {
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    @NotNull
    private String memberName;

    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;
}
