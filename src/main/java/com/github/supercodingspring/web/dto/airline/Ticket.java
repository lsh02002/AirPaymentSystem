package com.github.supercodingspring.web.dto.airline;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.supercodingspring.repository.airlineTicket.AirlineTicket;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Ticket {
    private String depart;
    private String arrival;
    private String departureTime;
    private String returnTime;
    private Integer ticketId;

    public Ticket() {
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public Ticket(AirlineTicket airlineTicket){
        this.ticketId = airlineTicket.getTicketId();
        this.depart = airlineTicket.getDepartureLocation();
        this.arrival = airlineTicket.getArrivalLocation();
        this.departureTime = airlineTicket.getDepartureAt().format(formatter);
        this.returnTime = airlineTicket.getReturnAt().format(formatter);
    }
}
