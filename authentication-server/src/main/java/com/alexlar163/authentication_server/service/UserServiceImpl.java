package com.alexlar163.authentication_server.service;

import com.alexlar163.authentication_server.entity.UserEntity;
import com.alexlar163.authentication_server.repository.UserRepository;
import com.alexlar163.authentication_server.service.interfaces.UserServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceInterface {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(UserEntity user) {
        userRepository.save(user);
    }
}
