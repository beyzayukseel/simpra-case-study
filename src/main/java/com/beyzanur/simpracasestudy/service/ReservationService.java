package com.beyzanur.simpracasestudy.service;

import com.beyzanur.simpracasestudy.domain.exception.ExceptionDescription;
import com.beyzanur.simpracasestudy.domain.exception.ExceptionHandler;
import com.beyzanur.simpracasestudy.entity.RateCode;
import com.beyzanur.simpracasestudy.entity.Reservation;
import com.beyzanur.simpracasestudy.entity.RoomCode;
import com.beyzanur.simpracasestudy.model.*;
import com.beyzanur.simpracasestudy.repository.RateCodeRepository;
import com.beyzanur.simpracasestudy.repository.ReservationRepository;
import com.beyzanur.simpracasestudy.repository.RoomCodeRepository;
import com.beyzanur.simpracasestudy.service.notification.NotificationSender;
import com.beyzanur.simpracasestudy.util.LoggerUtil;
import com.google.common.util.concurrent.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class ReservationService{
    private final RoomCodeRepository roomCodeRepository;
    private final RateCodeRepository rateCodeRepository;
    private final ReservationRepository reservationRepository;
    private final LoggerUtil loggerUtil;
    private final NotificationSender notificationSender;

    public String createReservation(CreateReservationRequest createReservationRequest) {
        createReservationRequest.validate();
        Optional<RateCode> rateCode = rateCodeRepository.findByCode(createReservationRequest.rateCode());
        if(rateCode.isEmpty()) {
            throw new ExceptionHandler(ExceptionDescription.RATE_CODE_CANNOT_BE_FOUND, HttpStatus.NOT_FOUND);
        }
        Optional<RoomCode> roomCode = roomCodeRepository.findByCode(createReservationRequest.roomCode());
        if(roomCode.isEmpty()) {
            throw new ExceptionHandler(ExceptionDescription.ROOM_CODE_CANNOT_BE_FOUND, HttpStatus.NOT_FOUND);
        }
        Reservation reservationEntity = createReservationRequest.convertToEntity();
        reservationEntity.setRateCode(rateCode.get());
        reservationEntity.setRoomCode(roomCode.get());
        reservationRepository.save(reservationEntity); // try catch
        NotificationModel notificationModel = new NotificationModel(
                reservationEntity.getConfirmationNumber(),
                "Reservation created successfully",
                reservationEntity.getCustomer().getPhone(),
                reservationEntity.getCustomer().getEmail(),
                reservationEntity.getCustomer().getFirstName(),
                reservationEntity.getCustomer().getLastName()
        );

        loggerUtil.logInformation("Reservation created successfully");
        sendAsyncNotification(notificationModel);
        return "";
    }

    public void sendAsyncNotification(NotificationModel notificationModel) {
        ListenableFuture<String> future = sendNotification(notificationModel);
        Futures.addCallback(future, new FutureCallback<>() {
            public void onSuccess(String result)
            {
                // to do
            }
            public void onFailure(Throwable t) {
                loggerUtil.logError("Error occurred while sending notification", new RuntimeException(""),
                        "Confirmation number:" + notificationModel.confirmationNumber());
            }
        }, MoreExecutors.directExecutor());
    }

    private ListenableFuture<String> sendNotification(NotificationModel notificationModel) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        return service.submit(() -> {
            try {
                notificationSender.sendNotification(notificationModel);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "Task completed successfully!";
        });
    }

    public void deleteReservation(Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isEmpty()) {
            loggerUtil.logWarn("Reservation could not be found");
            throw new ExceptionHandler(ExceptionDescription.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        reservation.get().setStatus(ReservationStatus.DELETED.toString());
        reservationRepository.save(reservation.get());
    }


    public void updateReservation(UpdateReservationRequest updateReservationRequest) {
        updateReservationRequest.validate();

        Optional<Reservation> reservationEntity = reservationRepository.findById(updateReservationRequest.reservationId());
        if(reservationEntity.isEmpty()) {
            throw new ExceptionHandler(ExceptionDescription.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        Optional<RateCode> rateCode = rateCodeRepository.findByCode(updateReservationRequest.rateCode());

        if(rateCode.isEmpty()) {
            loggerUtil.logWarn("Rate code could not be found");
            throw new ExceptionHandler(ExceptionDescription.RATE_CODE_CANNOT_BE_FOUND, HttpStatus.NOT_FOUND);
        }

        Optional<RoomCode> roomCode = roomCodeRepository.findByCode(updateReservationRequest.roomCode());
        if(roomCode.isEmpty()) {
            loggerUtil.logWarn("Room code could not be found");
            throw new ExceptionHandler(ExceptionDescription.ROOM_CODE_CANNOT_BE_FOUND, HttpStatus.NOT_FOUND);
        }

        updateReservationRequest.convertToEntity(reservationEntity.get());

        reservationEntity.get().setRateCode(rateCode.get());
        reservationEntity.get().setRoomCode(roomCode.get());

        reservationRepository.save(reservationEntity.get());
    }

    public List<Reservation> getInLastHourCheckoutReservations() {
        return reservationRepository.findCheckoutInLastHour(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
    }

    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public List<ReservationResponse> getReservationsByFilterCriteria(ReservationFilterRequest reservationFilterRequest) {
        reservationFilterRequest.validate();

        Optional<RateCode> rateCode = rateCodeRepository.findByCode(reservationFilterRequest.rateCode());

        if(rateCode.isEmpty()) {
            loggerUtil.logWarn("Rate code could not be found");
            throw new ExceptionHandler(ExceptionDescription.RATE_CODE_CANNOT_BE_FOUND, HttpStatus.NOT_FOUND);
        }

        Optional<RoomCode> roomCode = roomCodeRepository.findByCode(reservationFilterRequest.roomCode());
        if(roomCode.isEmpty()) {
            loggerUtil.logWarn("Room code could not be found");
            throw new ExceptionHandler(ExceptionDescription.ROOM_CODE_CANNOT_BE_FOUND, HttpStatus.NOT_FOUND);
        }

        List<Reservation> getFilteredReservations = reservationRepository.findByCriteria(
                reservationFilterRequest.checkInDate(),
                reservationFilterRequest.checkoutDate(),
                rateCode.get().getId(),
                roomCode.get().getId()
        );

        return getFilteredReservations.parallelStream().map(Reservation::convertToReservationResponse).toList();
    }
}
