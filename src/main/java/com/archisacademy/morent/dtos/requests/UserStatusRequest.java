package com.archisacademy.morent.dtos.requests;

import com.archisacademy.morent.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStatusRequest {
    @NotNull(message = "Status cannot be null")
    private User.UserStatus status;
}
