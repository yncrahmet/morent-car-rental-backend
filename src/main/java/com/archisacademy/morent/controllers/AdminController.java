package com.archisacademy.morent.controllers;

import com.archisacademy.morent.dtos.auth.LoginRequest;
import com.archisacademy.morent.dtos.auth.LoginResponse;
import com.archisacademy.morent.dtos.auth.RegisterRequest;
import com.archisacademy.morent.dtos.responses.BookingDetailsResponse;
import com.archisacademy.morent.dtos.responses.UserResponse;
import com.archisacademy.morent.services.abstracts.AdminService;
import com.archisacademy.morent.services.abstracts.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final BookingService bookingService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterRequest registerRequest) {
        try {
            adminService.registerAdmin(registerRequest);
            return ResponseEntity.ok(Map.of("message", "Admin registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Registration failed", "error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> adminLogin(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = adminService.adminLogin(loginRequest);
            return ResponseEntity.ok(loginResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(e.getMessage(), null));
        }
    }

/*
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> users= adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

 */
    @GetMapping("bookings")
    public ResponseEntity<List<BookingDetailsResponse>> getAllBookings() {
        List<BookingDetailsResponse> bookings = adminService.getAllBookingDetails();
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.status(HttpStatus.OK).body("Booking cancelled successfully");
    }
}
