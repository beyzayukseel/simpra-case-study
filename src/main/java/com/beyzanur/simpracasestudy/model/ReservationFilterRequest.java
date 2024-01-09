package com.beyzanur.simpracasestudy.model;

import com.beyzanur.simpracasestudy.domain.exception.ExceptionDescription;
import com.beyzanur.simpracasestudy.domain.exception.ExceptionHandler;
import org.springframework.http.HttpStatus;

public record ReservationFilterRequest (
        String checkInDate,
        String checkoutDate,
        String roomCode,
        String rateCode
) {

        public void validate() {
            if (checkInDate == null || checkInDate.isBlank()) {
                throw new ExceptionHandler(ExceptionDescription.CHECK_IN_DATE_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
            }
            if (checkoutDate == null || checkoutDate.isBlank()) {
                throw new ExceptionHandler(ExceptionDescription.CHECK_OUT_DATE_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
            }
            if (checkInDate.compareTo(checkoutDate) > 0) {
                throw new ExceptionHandler(ExceptionDescription.CHECK_OUT_DATE_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
            }
            if (roomCode == null || roomCode.isBlank()) {
                throw new ExceptionHandler(ExceptionDescription.ROOM_CODE_CANNOT_BE_NULL, HttpStatus.BAD_REQUEST);
            }
            if (rateCode == null || rateCode.isBlank()) {
                throw new ExceptionHandler(ExceptionDescription.RATE_CODE_CANNOT_BE_NULL, HttpStatus.BAD_REQUEST);
            }
        }
}
