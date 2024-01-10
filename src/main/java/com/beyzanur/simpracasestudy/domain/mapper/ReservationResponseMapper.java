package com.beyzanur.simpracasestudy.domain.mapper;

import com.beyzanur.simpracasestudy.entity.Reservation;
import com.beyzanur.simpracasestudy.model.ReservationResponse;

public class ReservationResponseMapper {
    public static ReservationResponse mapToDto(Reservation reservation) {
        return new ReservationResponse(
                reservation.getCustomer().getFirstName(),
                reservation.getCustomer().getLastName(),
                reservation.getCustomer().getEmail(),
                reservation.getCustomer().getPhone(),
                reservation.getId().toString(),
                reservation.getStatus(),
                reservation.getCheckinDate().toString(),
                reservation.getCheckoutDate().toString(),
                reservation.getRoomCode().getCode(),
                reservation.getRateCode().getCode(),
                reservation.getTotalPax()
        );
    }
}
