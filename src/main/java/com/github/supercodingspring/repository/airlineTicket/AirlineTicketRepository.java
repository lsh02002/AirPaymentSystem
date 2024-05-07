package com.github.supercodingspring.repository.airlineTicket;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface AirlineTicketRepository{

   Optional<List<AirlineTicket>> findAllAirlineTicketsWithPlaceAndTicketType(String likePlace, String ticketType);

    Optional<List<AirlineTicketAndFlightInfo>> findAllAirLineTicketAndFlightInfo(Integer airlineTicketId);
}
