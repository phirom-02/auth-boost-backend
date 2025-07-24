package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.user.User;
import com.geekybyte.auth_boost_backend.user.UserRepository;
import com.geekybyte.auth_boost_backend.user.dto.CreateUserDto;
import com.geekybyte.auth_boost_backend.user.mapper.CreateUserDtoToUser;
import org.springframework.stereotype.Service;

@Service
public class CreateUserServiceImpl implements CreateUserService {

    private final UserRepository userRepository;

    public CreateUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(CreateUserDto dto) {
        User user = CreateUserDtoToUser.map(dto);
        return userRepository.save(user);
    }
}
