package com.github.supercodingspring.repository.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PaymentJdbcTemplateDao implements PaymentRepository{
    private final JdbcTemplate template;

    public PaymentJdbcTemplateDao(@Qualifier("jdbcTemplate") JdbcTemplate template) {
        this.template = template;
    }

    static RowMapper<Payment> paymentRowMapper = ((rs, rowNums) ->
            new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("passenger_id"),
                    rs.getInt("reservation_id"),
                    rs.getDate("pay_at")
            ));

    @Override
    public void makePayment(Integer passengerId, Integer reservationId) {
        try {
            List<Payment> payments = template.query("SELECT * FROM payment WHERE passenger_id = ? AND reservation_id = ?", paymentRowMapper, passengerId, reservationId);

            if(!payments.isEmpty()){
                throw new Exception("이미 지불되었습니다.");
            }

            template.update("INSERT INTO payment(passenger_id, reservation_id, pay_at) VALUES (?, ?, ?)", passengerId, reservationId, LocalDateTime.now());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
