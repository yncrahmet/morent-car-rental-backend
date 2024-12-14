package com.archisacademy.morent.controllers;

import com.archisacademy.morent.dtos.requests.UserStatusRequest;
import com.archisacademy.morent.dtos.requests.VehicleUpdateRequest;
import com.archisacademy.morent.dtos.responses.UserResponse;
import com.archisacademy.morent.dtos.responses.VehicleUpdateResponse;
import com.archisacademy.morent.services.abstracts.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/status/{userId}")
    public ResponseEntity<UserResponse> updateUserStatus(@PathVariable Long userId,
                                                         @Valid @RequestBody UserStatusRequest userStatusRequest) {
        UserResponse response = userService.updateUserStatus(userId, userStatusRequest);
        return ResponseEntity.ok(response);
    }
}
