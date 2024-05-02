package com.github.supercodingspring.repository.reservations;

import com.github.supercodingspring.repository.users.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservationJdbcTemplateDao implements ReservationRepository {

    private final JdbcTemplate template;

    public ReservationJdbcTemplateDao(@Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    static RowMapper<Reservation> ReservationRowMapper = ((rs, rowNums) ->
            new Reservation(
                    rs.getInt("reservation_id"),
                    rs.getInt("passenger_id"),
                    rs.getInt("airline_ticket_id")
            ));

    @Override
    public Boolean saveReservation(Reservation reservation) {
        Integer rowNums = template.update("INSERT INTO reservation(passenger_id, airline_ticket_id, reservation_status, reserve_at) VALUES (? ,? , ?, ? )",
                                          reservation.getPassengerId(), reservation.getAirlineTicketId(), reservation.getReservationStatus(),
                                          new Date(Timestamp.valueOf(reservation.getReserveAt()).getTime()));
        return rowNums > 0;
    }

    @Override
    public List<Reservation> findReservationByPassengerIdAndTicketId(Integer passengerId, Integer ticketId) {
        return template.query("SELECT * FROM reservation WHERE passenger_id = ? AND airline_ticket_id = ? ", ReservationRowMapper, passengerId, ticketId);
    }

    @Override
    public void setReservationConfirmedById(Integer reservationId){
        try {
            template.update("UPDATE reservation SET reservation_status ='확정' WHERE reservation_id = ?", reservationId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
