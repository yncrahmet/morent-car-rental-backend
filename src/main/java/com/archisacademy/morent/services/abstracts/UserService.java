package com.archisacademy.morent.services.abstracts;


import com.archisacademy.morent.ApiResponse.ApiResponse;
import com.archisacademy.morent.dtos.requests.CreateUserRequest;

public interface UserService  {
    ApiResponse save(CreateUserRequest dto);
}
