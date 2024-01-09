package com.beyzanur.simpracasestudy.repository;

import com.beyzanur.simpracasestudy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
