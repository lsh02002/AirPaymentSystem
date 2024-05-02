package com.github.supercodingspring.repository.payment;

public interface PaymentRepository {
    void makePayment(Integer passengerId, Integer reservationId);
}
