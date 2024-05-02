package com.github.supercodingspring.service;

import com.github.supercodingspring.repository.passenger.Passenger;
import com.github.supercodingspring.repository.passenger.PassengerReposiotry;
import com.github.supercodingspring.repository.payment.PaymentRepository;
import com.github.supercodingspring.repository.reservations.Reservation;
import com.github.supercodingspring.repository.reservations.ReservationRepository;
import com.github.supercodingspring.web.dto.airline.PaymentRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AirPaymentService {

    PassengerReposiotry passengerReposiotry;
    ReservationRepository reservationRepository;
    PaymentRepository paymentRepository;

    @Transactional(transactionManager = "tm")
    public Integer makePayment(PaymentRequest request){

        List<Integer> userIds = request.getUserIds();
        List<Integer> airlineTicketIds = request.getAirlineTicketIds();

        if(userIds.size() != airlineTicketIds.size()){
            throw new RuntimeException("요청하신 사용자와 티켓의 갯수가 맛지 않습니다.");
        }

        Integer successPayment = 0;

        for(int i=0; i<userIds.size(); i++){
            Passenger passenger = passengerReposiotry.findPassengerByUserId(userIds.get(i));
            List<Reservation> reservations = reservationRepository.findReservationByPassengerIdAndTicketId(passenger.getPassengerId(), airlineTicketIds.get(i));

            if(reservations.size()>=2) throw new RuntimeException("예약 수가 2개이상입니다.");
            else if(reservations.isEmpty()){
                throw new RuntimeException("에약한 내용이 없습니다");
            }

            paymentRepository.makePayment(passenger.getPassengerId(), airlineTicketIds.get(i));

            reservationRepository.setReservationConfirmedById(reservations.get(0).getReservationId());

            successPayment++;
        }

        return successPayment;
    }
}
