package com.beyzanur.simpracasestudy.domain.validator;

import com.beyzanur.simpracasestudy.domain.exception.ExceptionDescription;
import com.beyzanur.simpracasestudy.domain.exception.ExceptionHandler;
import com.beyzanur.simpracasestudy.model.UpdateReservationRequest;
import org.springframework.http.HttpStatus;

public class UpdateReservationValidator {
    public static void validate(UpdateReservationRequest request) {
        if (request.reservationId() < 0 ) {
            throw new ExceptionHandler(ExceptionDescription.RESERVATION_ID_NOT_VALID, HttpStatus.BAD_REQUEST);
        }

        if (request.firstName() == null || request.firstName().isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.FIRST_NAME_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
        }
        if (request.lastName() == null || request.lastName().isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.LAST_NAME_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
        }
        if (request.email() == null || request.email().isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.EMAIL_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
        }
        if (request.phoneNumber() == null || request.phoneNumber().isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.PHONE_NUMBER_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
        }
        if (request.checkInDate() == null || request.checkInDate().isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.CHECK_IN_DATE_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
        }
        if (request.checkOutDate() == null || request.checkOutDate().isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.CHECK_OUT_DATE_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
        }
        if (request.checkOutDate().compareTo(request.checkInDate()) > 0) {
            throw new ExceptionHandler(ExceptionDescription.CHECK_OUT_DATE_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
        }
        if (request.roomCode() == null || request.roomCode().isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.ROOM_CODE_CANNOT_BE_NULL, HttpStatus.BAD_REQUEST);
        }
        if (request.rateCode() == null || request.rateCode().isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.RATE_CODE_CANNOT_BE_NULL, HttpStatus.BAD_REQUEST);
        }
        if (request.totalPax() < 1) {
            throw new ExceptionHandler(ExceptionDescription.TOTAL_PAX_CANNOT_BE_LESS_THAN_ONE, HttpStatus.BAD_REQUEST);
        }
    }
}
