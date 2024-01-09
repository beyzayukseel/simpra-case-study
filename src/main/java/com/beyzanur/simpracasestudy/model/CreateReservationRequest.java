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

    public void validate() {
        if (firstName == null || firstName.isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.FIRST_NAME_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
        }
        if (lastName == null || lastName.isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.LAST_NAME_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
        }
        if (email == null || email.isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.EMAIL_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
        }
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.PHONE_NUMBER_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
        }
        if (checkInDate == null || checkInDate.isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.CHECK_IN_DATE_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
        }
        if (checkOutDate == null || checkOutDate.isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.CHECK_OUT_DATE_CANNOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
        }
        if (checkInDate.compareTo(checkOutDate) > 0) {
            throw new ExceptionHandler(ExceptionDescription.CHECK_IN_DATE_CANNOT_BE_AFTER_CHECK_OUT_DATE, HttpStatus.BAD_REQUEST);
        }
        if (roomCode == null || roomCode.isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.ROOM_CODE_CANNOT_BE_NULL, HttpStatus.BAD_REQUEST);
        }
        if (rateCode == null || rateCode.isBlank()) {
            throw new ExceptionHandler(ExceptionDescription.RATE_CODE_CANNOT_BE_NULL, HttpStatus.BAD_REQUEST);
        }
        if (totalPax < 1) {
            throw new ExceptionHandler(ExceptionDescription.TOTAL_PAX_CANNOT_BE_LESS_THAN_ONE, HttpStatus.BAD_REQUEST);
        }
    }

    public Reservation convertToEntity() {
        Reservation reservationEntity = new Reservation();
        reservationEntity.setCreated(LocalDateTime.now().toString());
        reservationEntity.setUpdated(LocalDateTime.now().toString());
        reservationEntity.setCheckinDate(LocalDateTime.parse(checkInDate));
        reservationEntity.setCheckoutDate(LocalDateTime.parse(checkOutDate));
        reservationEntity.setTotalPax(totalPax);

        Customer customerEntity = new Customer();
        customerEntity.setLastName(lastName);
        customerEntity.setEmail(email);
        customerEntity.setPhone(phoneNumber);
        customerEntity.setCreated(LocalDateTime.now().toString());
        customerEntity.setUpdated(LocalDateTime.now().toString());

        reservationEntity.setCustomer(customerEntity);

        return reservationEntity;
    }

}
