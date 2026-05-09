package com.example.shopping.service;


import java.util.List;

import com.example.shopping.dto.UserDto;
import com.example.shopping.entity.User;

public interface UserService  {

    String signup(User user);

    UserDto login(String email, String password);

	List<UserDto> getAllUsers();

	User updateStatus(UserDto user);
}