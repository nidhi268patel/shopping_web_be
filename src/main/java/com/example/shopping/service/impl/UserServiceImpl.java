package com.example.shopping.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shopping.config.JwtUtil;
import com.example.shopping.dto.UserDto;
import com.example.shopping.entity.User;
import com.example.shopping.repo.UserRepository;
import com.example.shopping.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtil jwt;

	// 🟢 Signup
	@Override
	public String signup(User user) {

		if (repo.existsByEmail(user.getEmail())) {
			throw new RuntimeException("Email already exists");
		}

		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole("user");
		user.setStatus("Active");
		user.setCreatedAt(LocalDateTime.now());
		repo.save(user);

		return "User registered successfully";
	}

	// 🔵 Login
	@Override
	public UserDto login(String email, String password) {
		System.out.println("Email is: " + email); // ✅ print in console
		System.out.println("password is: " + password); // ✅ print in console

		User user = repo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	

		if (user.getStatus().equalsIgnoreCase("Suspended")) {
			throw new RuntimeException("Your account is suspended. Please contact support.");
		}
		if (!encoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid credentials");
		}

		String token = jwt.generateToken(user.getEmail());

		return new UserDto(token, user.getId(), user.getName(), user.getEmail(), user.getRole());
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> useList = repo.findAll();
		return useList.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private UserDto convertToDto(User user) {

		return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getStatus(),
				user.getCreatedAt());
	}

	@Override
	public User updateStatus(UserDto userdetails) {
		try {
			User user = repo.findById(userdetails.getId()).orElseThrow(() -> new RuntimeException("User not found"));
			if(user!=null) {
				user.setStatus(userdetails.getStatus());
				user.setUpdatedAt(LocalDateTime.now());
				repo.save(user);
				return user;
			}

			return null;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
