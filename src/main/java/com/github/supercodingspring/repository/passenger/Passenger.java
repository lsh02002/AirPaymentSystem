package com.github.supercodingspring.repository.passenger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    private Integer passengerId;
    private Integer userId;
    private String passportNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Passenger)) {
            return false;
        }

        Passenger passenger = (Passenger) o;

        return passengerId.equals(passenger.passengerId);
    }

    @Override
    public int hashCode() {
        return passengerId.hashCode();
    }
}
