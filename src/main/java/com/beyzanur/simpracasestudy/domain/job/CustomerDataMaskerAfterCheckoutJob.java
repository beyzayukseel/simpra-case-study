package com.beyzanur.simpracasestudy.domain.job;

import com.beyzanur.simpracasestudy.entity.Reservation;
import com.beyzanur.simpracasestudy.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerDataMaskerAfterCheckoutJob {

    private final ReservationService reservationService;

    // run every hour and check if there is any checkout in the last hour
    @Scheduled(cron = "0 0 0 * * *")
    public void maskCustomerDataAfterCheckout() {
        List<Reservation> reservations = reservationService.getCheckedOutReservations();
        reservations.forEach(this::maskCustomer);
    }

    private void maskCustomer(Reservation reservation) {
        String firstName = reservation.getCustomer().getFirstName();
        String lastName = reservation.getCustomer().getLastName();
        reservation.getCustomer().setFirstName(maskField(firstName));
        reservation.getCustomer().setLastName(maskField(lastName));
        reservationService.saveReservation(reservation);
    }

    private String maskField(String field) {
        if (field == null || field.length() < 2) {
            return field;
        }
        StringBuilder masked = new StringBuilder(field);

        for (char element: field.toCharArray()) {
            masked.setCharAt(element, '*');
        }

        return masked.toString();
    }
}
