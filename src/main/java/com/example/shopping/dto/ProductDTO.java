package com.example.shopping.dto;

import com.example.shopping.entity.Product;
import java.util.Base64;

public class ProductDTO {
    private Long productId;
    private String productName;
    private String productDescription;
    private Double sellingPrice;
    private Double mrpPrice;
    private Double discountPercent;
    private Integer availableStock;
    private String productCategory;
    private String imageContentType;
    private byte[] productImageData;
    private String imageBase64;

    // Map Entity → DTO (column names)
    public static ProductDTO fromEntity(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setProductDescription(product.getDescription());
        dto.setSellingPrice(product.getPrice());
        dto.setMrpPrice(product.getMrp());
        dto.setDiscountPercent(product.getDiscount());
        dto.setAvailableStock(product.getStock());
        dto.setProductCategory(product.getCategory());
        dto.setImageContentType(product.getImageType());
        dto.setProductImageData(product.getImageData());
        
        if (product.getImageData() != null) {
        	String base64 = Base64.getEncoder().encodeToString(product.getImageData());
            dto.setImageBase64("data:" + product.getImageType() + ";base64," + base64);
        }
        return dto;
    }

    // Map DTO → Entity
    public Product toEntity() {
        Product product = new Product();
        product.setId(productId);
        product.setName(productName);
        product.setDescription(productDescription);
        product.setPrice(sellingPrice);
        product.setMrp(mrpPrice);
        product.setDiscount(discountPercent);
        product.setStock(availableStock);
        product.setCategory(productCategory);
        product.setImageType(imageContentType);
        product.setImageData(productImageData);
        return product;
    }

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Double getMrpPrice() {
		return mrpPrice;
	}

	public void setMrpPrice(Double mrpPrice) {
		this.mrpPrice = mrpPrice;
	}

	public Double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Integer getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(Integer availableStock) {
		this.availableStock = availableStock;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public byte[] getProductImageData() {
		return productImageData;
	}

	public void setProductImageData(byte[] productImageData) {
		this.productImageData = productImageData;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
    
    
}
