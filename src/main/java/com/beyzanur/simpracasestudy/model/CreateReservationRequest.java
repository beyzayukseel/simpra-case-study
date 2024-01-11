package com.beyzanur.simpracasestudy.model;

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
