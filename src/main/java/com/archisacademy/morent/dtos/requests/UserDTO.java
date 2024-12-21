package com.archisacademy.morent.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    private UUID userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String status;
}
