package com.prospecta.challange.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prospecta.challange.model.Users;


public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
