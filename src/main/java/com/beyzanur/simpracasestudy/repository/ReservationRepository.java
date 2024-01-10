package com.beyzanur.simpracasestudy.repository;

import com.beyzanur.simpracasestudy.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.checkinDate = ?1 AND r.checkoutDate = ?2 AND r.rateCode.id = ?3 AND r.roomCode.id = ?4")
    List<Reservation> findByCriteria(String checkinDate, String checkoutDate, Long rateCodeId, Long roomCodeId);

    @Query("SELECT r FROM Reservation r WHERE r.checkoutDate < ?1 AND r.checkoutDate > ?2")
    List<Reservation> getCheckedOutReservations(LocalDateTime date1, LocalDateTime date2);
}
