package com.example.shopping.service.impl;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.shopping.dto.CreateOrderRequest;
import com.example.shopping.dto.ItemStatusHistoryDTO;
import com.example.shopping.dto.OrderDto;
import com.example.shopping.dto.OrderItemDto;
import com.example.shopping.dto.OrderItemRequest;
import com.example.shopping.dto.UpdateItemStatus;
import com.example.shopping.entity.Address;
import com.example.shopping.entity.ItemStatusHistory;
import com.example.shopping.entity.Order;
import com.example.shopping.entity.OrderItem;
import com.example.shopping.entity.Product;
import com.example.shopping.entity.User;
import com.example.shopping.repo.AddressRepository;
import com.example.shopping.repo.ItemStatusHistoryRepository;
import com.example.shopping.repo.OrderItemRepository;
import com.example.shopping.repo.OrderRepository;
import com.example.shopping.repo.ProductRepository;
import com.example.shopping.repo.UserRepository;
import com.example.shopping.service.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired 
    private OrderRepository orderRepo;
    @Autowired 
    private ProductRepository productRepo;
    @Autowired 
    private AddressRepository addressRepo;
    
    @Autowired
    private OrderItemRepository itemRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ItemStatusHistoryRepository historyRepository;
    
    @Autowired
    private EmailServiceImpl emailServiceImpl;

    private static final Map<String, Set<String>> ALLOWED_TRANSITIONS = Map.of(
            "CONFIRMED", Set.of("PACKED","CANCELLED"),
            "PACKED", Set.of("SHIPPED","CANCELLED"),
            "SHIPPED", Set.of("OUT_FOR_DELIVERY"),
            "OUT_FOR_DELIVERY", Set.of("DELIVERED"),
            "DELIVERED", Set.of(),   // Final state
            "CANCELLED", Set.of()    // Final state
    );
    @Override
    @Transactional
    public Order createOrder(CreateOrderRequest request) throws RuntimeException {
    	try {
    		   Order order = new Order();
    	        order.setUserId(request.getUserId());
    	        order.setOrderDate(LocalDateTime.now());
    	        order.setOverallStatus("PLACED");

    	        // Snapshot address
    	        Address address = addressRepo.findById(request.getAddressId())
    	                .orElseThrow(() -> new RuntimeException("Address not found"));
    	        order.setName(address.getName());
    	        order.setMobile(address.getMobile());
    	        order.setStreet(address.getStreet());
    	        order.setCity(address.getCity());
    	        order.setState(address.getState());
    	        order.setPincode(address.getPincode());
    	        order.setType(address.getType());

    	        List<OrderItem> items = new ArrayList<>();
                 double totalAmount=0;
    	        for (OrderItemRequest req : request.getItems()) {
    	            Product product = productRepo.findById(req.getProductId())
    	                    .orElseThrow(() -> new RuntimeException("Product not found"));
    	            if(product.getStock()<req.getQuantity()) {
    	            	throw new RuntimeException("Product out of stock "+ product.getName());
    	            }
    	            OrderItem item = new OrderItem();
    	            item.setOrder(order);
    	            item.setProduct(product);
    	            item.setProductName(product.getName());
    	            item.setPrice(product.getPrice());
    	            item.setQuantity(req.getQuantity());
    	            item.setStatus("CONFIRMED");
    	            //item.setShippingFee(req.getShippingFee());
    	            // Payment per item
    	            String method = request.getPaymentMethod() != null ? request.getPaymentMethod() : "COD";
    	            item.setPaymentMethod(method);
    	            if ("COD".equalsIgnoreCase(method)) {
    	                item.setPaymentStatus("PENDING");
    	            } else {
    	                item.setPaymentStatus("SUCCESS");
    	                item.setTransactionId(request.getTransactionId());
    	                item.setPaidAt(LocalDateTime.now());
    	            }

    	            // Amount
    	            double amount = (item.getPrice() * item.getQuantity()) + item.getShippingFee();
    	            item.setAmount(amount);
    	            totalAmount=amount;
    	            int stock=product.getStock()-req.getQuantity();
                    product.setStock(stock);
    	            // Status history
    	            ItemStatusHistory history = new ItemStatusHistory();
    	            history.setOrderItem(item);
    	            history.setStatus("CONFIRMED");
    	            history.setTimestamp(LocalDateTime.now());
    	            item.setStatusHistory(new ArrayList<>(List.of(history)));

    	            items.add(item);
    	        }
    	        order.setTotalAmount(totalAmount);
    	        order.setItems(items);
    	        
    	        User user =userRepository.findById(request.getUserId()).orElse(null);
    	        Order savedOrder = orderRepo.save(order);
    	        if(user!=null) {
    	        	emailServiceImpl.sendStatusMail(
    	        			user.getEmail(),
    	        			user.getName(),
    	        			order.getId(),
        	                "CONFIRMED",
        	                null
        	        );
    	        }
    	        
    	        return savedOrder;
		} catch (Exception e) {
			  throw new RuntimeException(e.getMessage());
		}
     
    }
    
    @Override
    public List<OrderItemDto> getItemsByUser(Long userId) {

        List<OrderItem> items = itemRepository.findItemsWithHistory(userId);

        return items.stream().sorted(Comparator.comparing(OrderItem::getId).reversed()).map(this::mapToDTO).toList();
    }

    private OrderItemDto mapToDTO(OrderItem item) {

        OrderItemDto dto = new OrderItemDto();

        dto.setItemId(item.getId());
        dto.setOrderId(item.getOrder().getId());
        if (item.getProduct().getImageData() != null) {
        	String base64 = Base64.getEncoder().encodeToString(item.getProduct().getImageData());
            dto.setImageBase64("data:" + item.getProduct().getImageType() + ";base64," + base64);
        }
      //  dto.setImageBase64(item.getProduct().getImageData());
        dto.setProductName(item.getProductName());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        dto.setShippingFee(item.getShippingFee());
        dto.setAmount(item.getAmount());

        dto.setStatus(item.getStatus());

        dto.setPaymentMethod(item.getPaymentMethod());
        dto.setPaymentStatus(item.getPaymentStatus());

        dto.setCourier(item.getCourierName());
        dto.setTrackingNumber(item.getTrackingNumber());

        // 📍 Address
        Order order = item.getOrder();
        dto.setName(order.getName());
        dto.setMobile(order.getMobile());
        dto.setStreet(order.getStreet());
        dto.setCity(order.getCity());
        dto.setState(order.getState());
        dto.setPincode(order.getPincode());

        // 🔥 STATUS HISTORY (IMPORTANT)
        if (item.getStatusHistory() != null) {

            List<ItemStatusHistoryDTO> history =
                    item.getStatusHistory().stream()
                    .sorted(Comparator.comparing(ItemStatusHistory::getTimestamp))
                    .map(h -> {
                    	ItemStatusHistoryDTO sh = new ItemStatusHistoryDTO();
                        sh.setStatus(h.getStatus());
                        sh.setTimestamp(h.getTimestamp());
                        return sh;
                    }).toList();

            dto.setHistory(history);
        }

        return dto;
    }
    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepo.findAll();
        return orders.stream().sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .map(this::mapToDTO)
                .toList();
    }

    private OrderDto mapToDTO(Order order) {

        List<OrderItemDto> itemDTOs = order.getItems().stream().map(item -> {

            List<ItemStatusHistoryDTO> history = item.getStatusHistory()
                    .stream()
                 //   .sorted(Comparator.comparing(ItemStatusHistory::getCreatedAt))
                    .map(h -> new ItemStatusHistoryDTO(
                            h.getStatus(),
                            h.getTimestamp()
                    ))
                    .toList();

            OrderItemDto dto = new OrderItemDto();
            dto.setItemId(item.getId());
            dto.setProductName(item.getProductName());
            dto.setQuantity(item.getQuantity());
            dto.setPrice(item.getPrice());
            dto.setShippingFee(item.getShippingFee());
            dto.setAmount(item.getAmount());
            dto.setStatus(item.getStatus());

            dto.setPaymentMethod(item.getPaymentMethod());
            dto.setPaymentStatus(item.getPaymentStatus());
            dto.setTransactionId(item.getTransactionId());
            dto.setPaidAt(item.getPaidAt());

            dto.setCourier(item.getCourierName());
            dto.setTrackingNumber(item.getTrackingNumber());
            dto.setExpectedDelivery(item.getExpectedDelivery());

            dto.setHistory(history);

            return dto;

        }).toList();

        OrderDto response = new OrderDto();

        response.setOrderId(order.getId());
        response.setUserId(order.getUserId());
        response.setOrderDate(order.getOrderDate());
        response.setOverallStatus(order.getOverallStatus());
        response.setTotalAmount(order.getTotalAmount());
        User user = userRepository.findById(order.getUserId()).orElse(null);
        response.setUserName(user.getName());
        response.setName(order.getName());
        response.setMobile(order.getMobile());
        response.setStreet(order.getStreet());
        response.setCity(order.getCity());
        response.setState(order.getState());
        response.setPincode(order.getPincode());
        response.setType(order.getType());

        response.setItems(itemDTOs);

        return response;
    }
    
    @Override
    @Transactional
    public UpdateItemStatus updateItemStatus(UpdateItemStatus request) {

        OrderItem item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));
        System.out.println("isnide updateItemStatus");

        if(request.getStatus().toUpperCase().equalsIgnoreCase(item.getStatus())) {
            throw new RuntimeException("please select valid status");

        }
        validate(item.getStatus(), request.getStatus());

        item.setStatus(request.getStatus());
        if(request.getStatus().equalsIgnoreCase("DELIVERED") && item.getPaymentMethod().equalsIgnoreCase("COD")) {
        	item.setPaymentStatus("PAID");
        	item.setPaidAt(LocalDateTime.now());
        }
        if(request.getStatus().equalsIgnoreCase("CANCELLED")) {
        	int stock=item.getQuantity()+item.getProduct().getStock();
        	item.getProduct().setStock(stock);
        	if(item.getPaymentStatus().equalsIgnoreCase("PAID")) {
        		item.setPaymentStatus("REFUND");
        		item.setPaidAt(LocalDateTime.now());
        	}
        }

        OrderItem saved = itemRepository.save(item);
        ItemStatusHistory history = new ItemStatusHistory();
        history.setOrderItem(saved);
        history.setStatus(request.getStatus());
        history.setTimestamp(LocalDateTime.now());
        
        historyRepository.save(history);
        request.setUpdatedAt(LocalDateTime.now());
        User user =userRepository.findById(item.getOrder().getUserId()).orElse(null);
        if(user!=null) {
            emailServiceImpl.sendStatusMail(user.getEmail(),item.getOrder().getName(),item.getOrder().getId(),item.getStatus(),item.getId());

        }
        return request;
    }

    private void validate(String current, String next) {

        if (current == null) return;

        Set<String> allowed = ALLOWED_TRANSITIONS.get(current);

        if (allowed == null || !allowed.contains(next)) {
            throw new RuntimeException(
                    "Invalid transition: " + current + " → " + next
            );
        }
    }
}
