package com.practicing.springpractice.service;

import com.practicing.springpractice.dto.RegistrationDto;
import com.practicing.springpractice.model.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
