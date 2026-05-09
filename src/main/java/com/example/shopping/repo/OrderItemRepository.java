package com.example.shopping.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.shopping.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	@Query("SELECT oi FROM OrderItem oi " +
		       "JOIN oi.order o " +
		       "LEFT JOIN FETCH oi.statusHistory sh " +
		       "WHERE o.userId = :userId " +
		       "ORDER BY oi.order.orderDate DESC")
		List<OrderItem> findItemsWithHistory(@Param("userId") Long userId);
}
