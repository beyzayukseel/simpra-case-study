package com.beyzanur.simpracasestudy.repository;

import com.beyzanur.simpracasestudy.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
