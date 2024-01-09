package com.beyzanur.simpracasestudy.controller;

import com.beyzanur.simpracasestudy.model.CreateReservationRequest;
import com.beyzanur.simpracasestudy.model.ReservationFilterRequest;
import com.beyzanur.simpracasestudy.model.UpdateReservationRequest;
import com.beyzanur.simpracasestudy.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody CreateReservationRequest createReservationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(createReservationRequest));
    }

    @GetMapping
    public void getReservationsByFilterCriteria(@PathVariable String checkInDate,
                                                @PathVariable String checkoutDate,
                                                @PathVariable String roomCode,
                                                @PathVariable String rateCode) {
        reservationService.getReservationsByFilterCriteria(new ReservationFilterRequest(
                checkInDate,
                checkoutDate,
                roomCode,
                rateCode)
        );
    }

    @PutMapping
    public void updateReservation(@RequestBody UpdateReservationRequest updateReservationRequest) {
        reservationService.updateReservation(updateReservationRequest);
    }

    @DeleteMapping
    public void deleteReservation(@RequestParam Long reservationId) {
        reservationService.deleteReservation(reservationId);
    }
}
