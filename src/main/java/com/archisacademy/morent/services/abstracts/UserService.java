package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.UserStatusRequest;
import com.archisacademy.morent.dtos.responses.UserResponse;

import java.util.UUID;

public interface UserService {
    UserResponse updateUserStatus( Long userId, UserStatusRequest userStatusRequest);
}
