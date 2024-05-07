package com.github.supercodingspring.repository.payment;

import com.github.supercodingspring.config.customExceptionHandler.CustomException;

public interface PaymentRepository {
    void makePayment(Integer passengerId, Integer reservationId) throws CustomException;
}
