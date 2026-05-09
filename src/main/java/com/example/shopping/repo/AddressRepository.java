package com.example.shopping.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserId(Long userId);
}