package com.beyzanur.simpracasestudy.model;

public record SaveReservationModel (
        long reservationId,
        String firstName,
        String lastName,
        String email,
        String reservationStatus,
        String checkInDate,
        String checkoutDate,
        long roomCodeId,
        long rateCodeId
) {

}
