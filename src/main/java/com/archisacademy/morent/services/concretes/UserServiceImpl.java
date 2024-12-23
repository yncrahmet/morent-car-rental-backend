package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.ApiResponse.ApiResponse;
import com.archisacademy.morent.ModelMappper.ModelMapperServiceImpl;
import com.archisacademy.morent.dtos.requests.CreateUserRequest;
import com.archisacademy.morent.dtos.requests.UserUpdateRequest;
import com.archisacademy.morent.dtos.responses.UserUpdateResponse;
import com.archisacademy.morent.entities.User;
import com.archisacademy.morent.jwt.JwtService;
import com.archisacademy.morent.repositories.UserRepository;
import com.archisacademy.morent.services.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapperServiceImpl modelMapperService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;


    public ApiResponse save(CreateUserRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = modelMapper.map(dto, User.class);
        user.setPassword(encodedPassword);
        user.setRoles(dto.getAuthorities());
        User savedUser = userRepository.save(user);

        return new ApiResponse(true, "İşlem başarılı.", savedUser);
    }

    @Override
    public UserUpdateResponse userUpdate(UUID userId, UserUpdateRequest userUpdateRequest){
        User user = userRepository.findByUserId(userId).orElseThrow(()->new RuntimeException("User not found!!!"));
        modelMapper.map(userUpdateRequest, user);
        userRepository.save(user);
        return new UserUpdateResponse("User has been updated successfully!!!");
    }
}

