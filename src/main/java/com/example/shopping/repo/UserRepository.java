package com.example.shopping.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
