package com.github.supercodingspring.repository.reservations;

import java.util.List;

public interface ReservationRepository {
    Boolean saveReservation(Reservation reservation);
    List<Reservation> findReservationByPassengerIdAndTicketId(Integer passengerId, Integer ticketId);

    void setReservationConfirmedById(Integer reservationId);
}
