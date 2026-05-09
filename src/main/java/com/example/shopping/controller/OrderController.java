package com.example.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.dto.CreateOrderRequest;
import com.example.shopping.dto.OrderDto;
import com.example.shopping.dto.OrderItemDto;
import com.example.shopping.dto.UpdateItemStatus;
import com.example.shopping.entity.Order;
import com.example.shopping.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    
    

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(request));
    }
    
    @GetMapping("/allorders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderItemDto>> getItems(
    		  @PathVariable("userId")  Long userId) {

        return ResponseEntity.ok(orderService.getItemsByUser(userId));
    }
    
    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody UpdateItemStatus request) {
        System.out.println("isnide controller");
        return ResponseEntity.ok(orderService.updateItemStatus(request));
    }
}