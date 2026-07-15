package com.society.maintenance.service;

import com.society.maintenance.dto.AuthResponse;
import com.society.maintenance.dto.LoginRequest;
import com.society.maintenance.dto.RegisterRequest;
import com.society.maintenance.dto.UserResponse;
import com.society.maintenance.entity.User;
import com.society.maintenance.repository.UserRepository;
import com.society.maintenance.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // =========================
    // Entity -> DTO Converter
    // =========================
    private UserResponse convertToResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setApartmentNumber(user.getApartmentNumber());

        if (user.getRole() != null) {
            response.setRole(user.getRole().name());
        }

        response.setCreatedAt(user.getCreatedAt());

        return response;
    }

    // =========================
    // Find User Entity
    // =========================
    private User findUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    // =========================
    // Create User
    // =========================
    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return userRepository.save(user);
    }

    // =========================
    // Get All Users
    // =========================
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // =========================
    // Get User By ID
    // =========================
    public UserResponse getUserById(Long id) {

        return convertToResponse(findUserById(id));
    }

    // =========================
    // Get User By Email
    // =========================
    public UserResponse getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return convertToResponse(user);
    }

    // =========================
    // Update User
    // =========================
    public User updateUser(Long id, User updatedUser) {

        User existingUser = findUserById(id);

        existingUser.setName(updatedUser.getName());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setApartmentNumber(updatedUser.getApartmentNumber());

        return userRepository.save(existingUser);
    }

    // =========================
    // Delete User
    // =========================
    public void deleteUser(Long id) {

        User user = findUserById(id);

        userRepository.delete(user);
    }

    // =========================
    // Register
    // =========================
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setPhone(request.getPhone());
        user.setApartmentNumber(request.getApartmentNumber());

        if (request.getRole() != null &&
                request.getRole().equalsIgnoreCase("ADMIN")) {

            user.setRole(User.Role.ADMIN);

        } else if (request.getRole() != null &&
                request.getRole().equalsIgnoreCase("STAFF")) {

            user.setRole(User.Role.STAFF);

        } else {

            user.setRole(User.Role.RESIDENT);
        }

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                "Registration Successful",
                user.getRole().name()
        );
    }

    // =========================
    // Login
    // =========================
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                "Login Successful",
                user.getRole().name()
        );
    }
}