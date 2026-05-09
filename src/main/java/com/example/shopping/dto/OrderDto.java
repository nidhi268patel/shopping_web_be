package com.example.shopping.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
	  private Long orderId;
	    private Long userId;
	    private LocalDateTime orderDate;
	    private String overallStatus;
	    private double totalAmount;

	    // Address snapshot
	    private String userName;
	    private String name;
	    private String mobile;
	    private String street;
	    private String city;
	    private String state;
	    private String pincode;
	    private String type;

	    private List<OrderItemDto> items;

		public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public LocalDateTime getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(LocalDateTime orderDate) {
			this.orderDate = orderDate;
		}

		public String getOverallStatus() {
			return overallStatus;
		}

		public void setOverallStatus(String overallStatus) {
			this.overallStatus = overallStatus;
		}

		public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(double toatalAmount) {
			this.totalAmount = toatalAmount;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getPincode() {
			return pincode;
		}

		public void setPincode(String pincode) {
			this.pincode = pincode;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public List<OrderItemDto> getItems() {
			return items;
		}

		public void setItems(List<OrderItemDto> items) {
			this.items = items;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}
	    
	    
}
