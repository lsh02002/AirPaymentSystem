package com.github.supercodingspring.web.dto.airline;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PassengerDto {
    private Integer passengerId;
    private Integer userId;
    private String passportNum;
}
