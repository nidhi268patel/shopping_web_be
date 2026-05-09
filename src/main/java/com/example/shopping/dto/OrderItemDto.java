package com.example.shopping.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OrderItemDto {

	    private Long itemId;
	    private Long orderId;

	    private String productName;
	    private String imageBase64;
	    private int quantity;
	    private double price;
	    private double shippingFee;
	    private double amount;

	    private String status;

	    private String paymentMethod;
	    private String paymentStatus;
	    private String transactionId;
		private LocalDateTime paidAt;
	    private String courier;
	    private String trackingNumber;
		

		private String courierName;
		private LocalDate expectedDelivery;

	    // 📍 Address
	    private String name;
	    private String mobile;
	    private String street;
	    private String city;
	    private String state;
	    private String pincode;

	    // 🧾 History
	    private List<ItemStatusHistoryDTO> history;

		public Long getItemId() {
			return itemId;
		}

		public void setItemId(Long itemId) {
			this.itemId = itemId;
		}

		public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public double getShippingFee() {
			return shippingFee;
		}

		public void setShippingFee(double shippingFee) {
			this.shippingFee = shippingFee;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getPaymentMethod() {
			return paymentMethod;
		}

		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}

		public String getPaymentStatus() {
			return paymentStatus;
		}

		public void setPaymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
		}

		public String getCourier() {
			return courier;
		}

		public void setCourier(String courier) {
			this.courier = courier;
		}

		public String getTrackingNumber() {
			return trackingNumber;
		}

		public void setTrackingNumber(String trackingNumber) {
			this.trackingNumber = trackingNumber;
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

		public List<ItemStatusHistoryDTO> getHistory() {
			return history;
		}

		public void setHistory(List<ItemStatusHistoryDTO> history) {
			this.history = history;
		}

		public String getImageBase64() {
			return imageBase64;
		}

		public void setImageBase64(String imageBase64) {
			this.imageBase64 = imageBase64;
		}

		public String getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}

		public LocalDateTime getPaidAt() {
			return paidAt;
		}

		public void setPaidAt(LocalDateTime paidAt) {
			this.paidAt = paidAt;
		}

		public String getCourierName() {
			return courierName;
		}

		public void setCourierName(String courierName) {
			this.courierName = courierName;
		}

		public LocalDate getExpectedDelivery() {
			return expectedDelivery;
		}

		public void setExpectedDelivery(LocalDate expectedDelivery) {
			this.expectedDelivery = expectedDelivery;
		}

	    // getters/setters
	    
}
