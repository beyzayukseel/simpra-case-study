package com.beyzanur.simpracasestudy.domain.job;

import com.beyzanur.simpracasestudy.entity.Reservation;
import com.beyzanur.simpracasestudy.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MaskCustomerDataAfterCheckoutJob {

    private final ReservationService reservationService;

    // run every hour and check if there is any checkout in the last hour
    @Scheduled(cron =  "0 30 10 * * *")
    public void maskCustomerDataAfterCheckout() {
        List<Reservation> reservations = reservationService.getInLastHourCheckoutReservations();
        reservations.forEach(reservation -> maskCustomer(reservation) );
    }

    private void maskCustomer(Reservation reservation) {
        reservation.getCustomer().setFirstName("*****");
        reservation.getCustomer().setLastName("*****");
        reservationService.saveReservation(reservation);
    }
}
