package com.archisacademy.morent.controllers;

import com.archisacademy.morent.ApiResponse.ApiResponse;
import com.archisacademy.morent.dtos.auth.LoginRequest;
import com.archisacademy.morent.dtos.auth.LoginResponse;
import com.archisacademy.morent.dtos.auth.RegisterRequest;
import com.archisacademy.morent.dtos.auth.RegisterResponse;
import com.archisacademy.morent.dtos.requests.CreateUserRequest;
import com.archisacademy.morent.dtos.requests.NotificationRequest;
import com.archisacademy.morent.dtos.requests.UserUpdateRequest;
import com.archisacademy.morent.dtos.responses.NotificationResponse;
import com.archisacademy.morent.dtos.responses.UserUpdateResponse;
import com.archisacademy.morent.dtos.responses.UserResponse;
import com.archisacademy.morent.services.concretes.AuthServiceImpl;
import com.archisacademy.morent.services.concretes.NotificationServiceImpl;
import com.archisacademy.morent.services.concretes.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final AuthServiceImpl authService;
    private final NotificationServiceImpl notificationService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody CreateUserRequest createUserRequest) {
        ApiResponse save = userService.save(createUserRequest);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserUpdateResponse> userUpdate(@PathVariable UUID userId, @Valid @RequestBody UserUpdateRequest userUpdateRequest){
        UserUpdateResponse response = userService.userUpdate(userId, userUpdateRequest);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/status/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserUpdateResponse> updateUserStatus(@PathVariable Long userId) {
        UserUpdateResponse response = userService.updateUserStatus(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{userId}/notifications")
    public ResponseEntity<List<NotificationRequest>> getUserNotifications(@PathVariable Long userId) {
        return notificationService.getNotificationsForUser(userId);
    }




}