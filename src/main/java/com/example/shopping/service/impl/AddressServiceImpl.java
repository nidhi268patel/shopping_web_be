package com.example.shopping.service.impl;

import org.springframework.stereotype.Service;

import com.example.shopping.entity.Address;
import com.example.shopping.repo.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class AddressServiceImpl {
    @Autowired
    private AddressRepository repo;

    public List<Address> getAddresses(Long userId) {
        return repo.findByUserId(userId);
    }

    public Address saveAddress(Address address) {
        return repo.save(address);
    }

    public Address updateAddress(Long id, Address address) {
        address.setId(id);
        return repo.save(address);
    }

    public void deleteAddress(Long id) {
        repo.deleteById(id);
    }

    public void setDefault(Long userId, Long id) {
        List<Address> addresses = repo.findByUserId(userId);
        for (Address addr : addresses) {
            addr.setIsDefault(addr.getId().equals(id));
            repo.save(addr);
        }
    }
}

