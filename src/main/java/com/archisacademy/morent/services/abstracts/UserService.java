package com.archisacademy.morent.services.abstracts;


import com.archisacademy.morent.ApiResponse.ApiResponse;
import com.archisacademy.morent.dtos.requests.CreateUserRequest;
import com.archisacademy.morent.dtos.requests.UserUpdateRequest;
import com.archisacademy.morent.dtos.responses.UserUpdateResponse;

import java.util.UUID;

public interface UserService  {
    ApiResponse save(CreateUserRequest dto);

    UserUpdateResponse userUpdate(UUID userId, UserUpdateRequest userUpdateRequest);
}
