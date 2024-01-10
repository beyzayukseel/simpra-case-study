package com.beyzanur.simpracasestudy.domain.mapper;

import com.beyzanur.simpracasestudy.entity.Customer;
import com.beyzanur.simpracasestudy.entity.Reservation;
import com.beyzanur.simpracasestudy.model.CreateReservationRequest;

import java.time.LocalDateTime;

public class ReservationEntityMapper {
    public static Reservation mapToEntity(CreateReservationRequest createReservationRequest) {
        Reservation reservationEntity = new Reservation();
        reservationEntity.setCreated(LocalDateTime.now().toString());
        reservationEntity.setUpdated(LocalDateTime.now().toString());
        reservationEntity.setCheckinDate(LocalDateTime.parse(createReservationRequest.checkInDate()));
        reservationEntity.setCheckoutDate(LocalDateTime.parse(createReservationRequest.checkOutDate()));
        reservationEntity.setTotalPax(createReservationRequest.totalPax());

        Customer customerEntity = new Customer();
        customerEntity.setLastName(createReservationRequest.lastName());
        customerEntity.setEmail(createReservationRequest.email());
        customerEntity.setPhone(createReservationRequest.phoneNumber());
        customerEntity.setCreated(LocalDateTime.now().toString());
        customerEntity.setUpdated(LocalDateTime.now().toString());

        reservationEntity.setCustomer(customerEntity);

        return reservationEntity;
    }
}
