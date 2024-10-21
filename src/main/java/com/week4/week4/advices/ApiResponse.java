package com.week4.week4.advices;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * ApiResponse
 */
@Data
public class ApiResponse<T> {
    // @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime localDateTime;
    private T data;
    private ApiError apiError;

    public ApiResponse() {
        this.localDateTime = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError apiError) {
        this();
        this.apiError = apiError;
    }

}
