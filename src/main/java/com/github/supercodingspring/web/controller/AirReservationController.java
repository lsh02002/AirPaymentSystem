package com.github.supercodingspring.web.controller;

import com.github.supercodingspring.config.customExceptionHandler.CustomException;
import com.github.supercodingspring.service.AirReservationService;
import com.github.supercodingspring.web.dto.airline.*;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/air-reservation")
@AllArgsConstructor
@Slf4j
public class AirReservationController {

    private final AirReservationService airReservationService;

    @ApiOperation(value = "아이디와 티켓 종류로 조회", notes = "유저 아이디와 티켓 종류를 이용하여 조회합니다.")
    @GetMapping("/tickets")
    public TicketResponse findAirlineTickets(@RequestParam("user-Id") Integer userId,
                                             @RequestParam("airline-ticket-type") String ticketType ) throws CustomException {
        log.info("장소 조회를 시작합니다");

        List<Ticket> tickets = airReservationService.findUserFavoritePlaceTickets(userId, ticketType);
        return new TicketResponse(tickets);
    }

    @ApiOperation(value = "예약 하기", notes = "예약 정보를 입력하여 예약을 합니다.")
    @PostMapping("/reservations")
    public ReservationResult makeReservation(@RequestBody ReservationRequest reservationRequest) throws CustomException {

        log.info("예약 작업을 시작합니다");

        return airReservationService.makeReservation(reservationRequest);
    }
}
