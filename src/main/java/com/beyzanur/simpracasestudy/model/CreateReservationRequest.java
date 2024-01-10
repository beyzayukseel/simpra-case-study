package com.beyzanur.simpracasestudy.model;

import com.beyzanur.simpracasestudy.domain.exception.ExceptionDescription;
import com.beyzanur.simpracasestudy.domain.exception.ExceptionHandler;
import com.beyzanur.simpracasestudy.entity.Customer;
import com.beyzanur.simpracasestudy.entity.Reservation;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record CreateReservationRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String checkInDate,
        String checkOutDate,
        String roomCode,
        String rateCode,
        int totalPax
) {

}
