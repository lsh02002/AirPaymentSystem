package com.github.supercodingspring.config.customExceptionHandler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends Exception{
    private final ExceptionStatus exceptionStatus;
}
