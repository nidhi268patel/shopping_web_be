package com.example.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.dto.UserDto;
import com.example.shopping.entity.User;
import com.example.shopping.service.UserService;
import com.example.shopping.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class userController {

    @Autowired
    private UserServiceImpl service;

    // 🟢 Signup
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
    	  String result = service.signup(user);

    	    return ResponseEntity.ok(result);    }

    // 🔵 Login
    @GetMapping("/login")
    public UserDto login( @RequestParam("email") String email,
    		@RequestParam("password") String password) {
        return service.login(email, password);
    }
    
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> users = service.getAllUsers();

        return ResponseEntity.ok(users);
    }
 // Change Status API
    @PostMapping("/status")
    public User updateStatus(
            @RequestBody UserDto user
    ) {
        return service.updateStatus(user);
    }

    // Change Role API
//    @PatchMapping("/{id}/role")
//    public User updateRole(
//            @RequestBody UserDto user
//
//    ) {
//        return userService.updateRole(user);
//    }
}