package com.beyzanur.simpracasestudy.repository;

import com.beyzanur.simpracasestudy.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
