package com.beyzanur.simpracasestudy.model;


public record ReservationResponse (
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String reservationId,
        String reservationStatus,
        String checkInDate,
        String checkoutDate,
        String roomCode,
        String rateCode,
        int totalPax
) {

}
