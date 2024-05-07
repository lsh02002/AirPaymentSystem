package com.github.supercodingspring.service;

import com.github.supercodingspring.config.customExceptionHandler.CustomException;
import com.github.supercodingspring.config.customExceptionHandler.ExceptionStatus;
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

    @Transactional(transactionManager = "tm2")
    public Integer makePayment(PaymentRequest request) throws CustomException {

        List<Integer> userIds = request.getUserIds();
        List<Integer> airlineTicketIds = request.getAirlineTicketIds();

        if(userIds.size() != airlineTicketIds.size()){
            throw new CustomException(ExceptionStatus.BAD_REQUEST);
        }

        Integer successPayment = 0;

        for(int i=0; i<userIds.size(); i++){
            Passenger passenger = passengerReposiotry.findPassengerByUserId(userIds.get(i)).get();
            List<Reservation> reservations = reservationRepository.findReservationByPassengerIdAndTicketId(passenger.getPassengerId(), airlineTicketIds.get(i)).get();

            if(reservations.size()>=2) throw new CustomException(ExceptionStatus.INVALID_RESPONSE);
            else if(reservations.isEmpty()){
                throw new CustomException(ExceptionStatus.POST_IS_EMPTY);
            }

            paymentRepository.makePayment(passenger.getPassengerId(), airlineTicketIds.get(i));

            reservationRepository.setReservationConfirmedById(reservations.get(0).getReservationId());

            successPayment++;
        }

        return successPayment;
    }
}
