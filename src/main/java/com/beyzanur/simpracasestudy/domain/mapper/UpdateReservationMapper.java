package com.beyzanur.simpracasestudy.domain.mapper;

import com.beyzanur.simpracasestudy.entity.Customer;
import com.beyzanur.simpracasestudy.entity.Reservation;

import java.time.LocalDateTime;

public class UpdateReservationMapper {

    public static Reservation mapToEntity(Reservation reservationEntity) {
        reservationEntity.setUpdated(LocalDateTime.now().toString());
        reservationEntity.setCheckinDate(LocalDateTime.parse(reservationEntity.getCheckinDate().toString()));
        reservationEntity.setCheckoutDate(LocalDateTime.parse(reservationEntity.getCheckoutDate().toString()));
        reservationEntity.setTotalPax(reservationEntity.getTotalPax());

        Customer customerEntity = new Customer();
        customerEntity.setId(reservationEntity.getCustomer().getId());
        customerEntity.setFirstName(reservationEntity.getCustomer().getFirstName());
        customerEntity.setLastName(reservationEntity.getCustomer().getLastName());
        customerEntity.setEmail(reservationEntity.getCustomer().getEmail());
        customerEntity.setPhone(reservationEntity.getCustomer().getPhone());

        reservationEntity.setCustomer(customerEntity);

        return reservationEntity;
    }

}
