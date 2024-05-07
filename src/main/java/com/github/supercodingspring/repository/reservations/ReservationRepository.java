package com.github.supercodingspring.repository.reservations;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Boolean saveReservation(Reservation reservation);
    Optional<List<Reservation>> findReservationByPassengerIdAndTicketId(Integer passengerId, Integer ticketId);

    void setReservationConfirmedById(Integer reservationId);
}
