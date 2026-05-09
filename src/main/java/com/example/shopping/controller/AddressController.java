package com.example.shopping.controller;

import org.springframework.web.bind.annotation.*;

import com.example.shopping.entity.Address;
import com.example.shopping.service.impl.AddressServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin(origins = "*") // allow Angular frontend
public class AddressController {
    @Autowired
    private AddressServiceImpl service;

    @GetMapping
    public List<Address> getAddresses(@RequestParam("userId") Long userId) {
        return service.getAddresses(userId);
    }

    @PostMapping
    public Address addAddress(@RequestBody Address address) {
        return service.saveAddress(address);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Long id, @RequestBody Address address) {
        return service.updateAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable("id") Long id) {
        service.deleteAddress(id);
    }

    @PutMapping("/{id}/default")
    public void setDefault(@PathVariable Long id, @RequestParam Long userId) {
        service.setDefault(userId, id);
    }
}
