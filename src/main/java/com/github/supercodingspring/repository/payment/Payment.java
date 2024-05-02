package com.github.supercodingspring.repository.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Payment {
    private Integer paymentId;
    private Integer passengerId;
    private Integer reservationId;
    private Date payAt;
}
