package com.example.shopping.dto;

import java.time.LocalDateTime;

public class ItemStatusHistoryDTO {

	    private String status;
	    private LocalDateTime timestamp;
       
		public ItemStatusHistoryDTO() {
			super();
		}
		public ItemStatusHistoryDTO(String status, LocalDateTime timestamp) {
			this.status = status;
			this.timestamp = timestamp;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public LocalDateTime getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}

	    // getters/setters
}
