package com.archisacademy.morent.services.abstracts;


import com.archisacademy.morent.ApiResponse.ApiResponse;
import com.archisacademy.morent.dtos.requests.CreateUserRequest;
import com.archisacademy.morent.dtos.responses.UserResponse;

public interface UserService  {
    ApiResponse save(CreateUserRequest dto);

    UserResponse updateUserStatus(Long userId);
}
