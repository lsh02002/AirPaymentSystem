package com.github.supercodingspring.web.dto.airline;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class TicketResponse {
    private List<Ticket> tickets;
}
