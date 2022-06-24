package com.plunex.emilokan.modules.user;


import com.plunex.emilokan.exception.CustomException;
import com.plunex.emilokan.modules.user.dto.UserRequest;
import com.plunex.emilokan.modules.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getById(String userId) {

        return userRepository.findById(UUID.fromString(userId)).orElseThrow(
                () -> new CustomException("Resource Not Found", HttpStatus.NOT_FOUND));
    }

    @Override
    public UserResponse getUserResponseById(String userId) {
        User existingUser = getById(userId);

        return new UserResponse(
                existingUser.getFirstName(),
                existingUser.getLastName(),
                existingUser.getUserName(),
                existingUser.getPhoneNumber(),
                existingUser.getEmail());
    }

    @Override
    public UserResponse create(UserRequest request) {
        User newUser = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .userName(request.getUserName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .build();

        User savedUser = userRepository.save(newUser);

        return new UserResponse(
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getUserName(),
                savedUser.getPhoneNumber(),
                savedUser.getEmail());
    }
}
