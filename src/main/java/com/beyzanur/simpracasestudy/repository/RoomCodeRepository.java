package com.beyzanur.simpracasestudy.repository;

import com.beyzanur.simpracasestudy.entity.RoomCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomCodeRepository extends JpaRepository<RoomCode, Long> {
    @Query
    Optional<RoomCode> findByCode(String code);
}
