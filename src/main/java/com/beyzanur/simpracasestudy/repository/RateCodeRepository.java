package com.beyzanur.simpracasestudy.repository;

import com.beyzanur.simpracasestudy.entity.RateCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateCodeRepository extends JpaRepository<RateCode, Long> {
    @Query
    Optional<RateCode> findByCode(String code);
}
