package com.example.shopping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;       // Link to user
    private String name;       // Full name
    private String mobile;
    private String pincode;
    private String street;
    private String city;
    private String state;
    private String type;       // Home / Work / Other
    private Boolean isDefault = false;

    // ---------------- Constructors ----------------
    public Address() {}

    public Address(Long userId, String name, String mobile, String pincode,
                   String street, String city, String state, String type, Boolean isDefault) {
        this.userId = userId;
        this.name = name;
        this.mobile = mobile;
        this.pincode = pincode;
        this.street = street;
        this.city = city;
        this.state = state;
        this.type = type;
        this.isDefault = isDefault;
    }

    // ---------------- Getters & Setters ----------------
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
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

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
}
