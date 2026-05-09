package com.example.shopping.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.entity.ItemStatusHistory;

public interface ItemStatusHistoryRepository extends JpaRepository<ItemStatusHistory, Long> {

}
