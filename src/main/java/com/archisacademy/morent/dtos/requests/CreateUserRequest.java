package com.archisacademy.morent.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "İsim boş bırakılamaz.")
    @Size(min = 2, max = 50, message = "İsim en az 2, en fazla 50 karakter olmalıdır.")
    private String name;

    @NotBlank(message = "Kullanıcı adı boş bırakılamaz.")
    @Size(min = 3, max = 30, message = "Kullanıcı adı en az 3, en fazla 30 karakter olmalıdır.")
    private String userName;

    @NotBlank(message = "E-posta boş bırakılamaz.")
    private String email;


    @Size(min = 8, max = 20, message = "Şifre en az 8, en fazla 20 karakter olmalıdır.")
    @NotBlank
    private String password;

    private boolean active;
}