package com.example.shopping.dto;

import java.util.List;

public class CreateOrderRequest {
	 private Long userId;
	    private Long addressId;
	    private String paymentMethod;   // COD, UPI, CARD
	    private String transactionId;   // optional for prepaid
	    private List<OrderItemRequest> items;
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public Long getAddressId() {
			return addressId;
		}
		public void setAddressId(Long addressId) {
			this.addressId = addressId;
		}
		public String getPaymentMethod() {
			return paymentMethod;
		}
		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}
		public String getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}
		public List<OrderItemRequest> getItems() {
			return items;
		}
		public void setItems(List<OrderItemRequest> items) {
			this.items = items;
		}
	    
}
