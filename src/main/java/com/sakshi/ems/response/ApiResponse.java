package com.sakshi.ems.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;

    private String message;

    private LocalDateTime timestamp;

    private T data;

    // Null for normal CRUD operations
    private PaginationMeta meta;
}