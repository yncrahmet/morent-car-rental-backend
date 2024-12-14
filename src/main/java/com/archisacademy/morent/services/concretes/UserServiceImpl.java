package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.UserStatusRequest;
import com.archisacademy.morent.dtos.responses.UserResponse;
import com.archisacademy.morent.entities.User;
import com.archisacademy.morent.repositories.UserRepository;
import com.archisacademy.morent.services.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse updateUserStatus(Long userId, UserStatusRequest userStatusRequest) {
        User user = userRepository.findUserById(userId).orElse(null);
        if (user == null) {
            return new UserResponse("User not found");
        }
        User.UserStatus requestedStatus = userStatusRequest.getStatus();
        if (user.getStatus() == requestedStatus) {
            return new UserResponse("User  status is already " + requestedStatus);
        }

        user.setStatus(requestedStatus);
        userRepository.save(user);

        return new UserResponse("User  status updated successfully");
    }

}
