package com.github.supercodingspring.repository.passenger;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Passenger {
    private Integer passengerId;
    private Integer userId;
    private String passportNum;
}
