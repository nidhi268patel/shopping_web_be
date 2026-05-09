package com.example.shopping.service;

import java.util.List;

import com.example.shopping.dto.CreateOrderRequest;
import com.example.shopping.dto.OrderDto;
import com.example.shopping.dto.OrderItemDto;
import com.example.shopping.dto.UpdateItemStatus;
import com.example.shopping.entity.Order;

public interface OrderService {

	Order createOrder(CreateOrderRequest request);

	List<OrderItemDto> getItemsByUser(Long userId);

	List<OrderDto> getAllOrders();

	UpdateItemStatus updateItemStatus(UpdateItemStatus request);

}
