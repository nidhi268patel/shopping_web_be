package com.example.shopping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;

	@Column(name = "product_name", nullable = false, length = 255)
	private String name;

	@Column(name = "product_description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "selling_price", nullable = false, precision = 10)
	private Double price;

	@Column(name = "mrp_price", nullable = false, precision = 10)
	private Double mrp;

	@Column(name = "discount_percent", precision = 5)
	private Double discount;

	@Column(name = "available_stock")
	private Integer stock;

	@Column(name = "product_category", length = 100)
	private String category;

	@Lob
	@Column(name = "product_image_data", length = 10485760) // 10MB
	private byte[] imageData;

	@Column(name = "image_content_type", length = 50)
	private String imageType;


	// All Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}


}
