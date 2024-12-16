package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.ApiResponse.ApiResponse;
import com.archisacademy.morent.ModelMappper.ModelMapperServiceImpl;
import com.archisacademy.morent.dtos.requests.CreateUserRequest;
import com.archisacademy.morent.dtos.requests.UserDTO;
import com.archisacademy.morent.entities.User;
import com.archisacademy.morent.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  {
        private final UserRepository userRepository;
        private final ModelMapperServiceImpl modelMapperService;
    public UserServiceImpl(UserRepository userRepository, ModelMapperServiceImpl modelMapperService) {
        this.userRepository = userRepository;
        this.modelMapperService = modelMapperService;
    }

    public ApiResponse save(CreateUserRequest dto) {
        User user = this.modelMapperService.request().map(dto,User.class);//burada parantez içindeki veriyi dto yu , User.class a çevir diyoruz. Dto içinde tam tersini yapıyoruz.
        User save = userRepository.save(user);
        return new ApiResponse<>(true,"İşlem başarılı.",save);
    }
}
