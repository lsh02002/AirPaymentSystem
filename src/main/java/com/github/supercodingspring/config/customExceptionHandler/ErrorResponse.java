package com.github.supercodingspring.config.customExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private Integer code;
    private String message;
}
