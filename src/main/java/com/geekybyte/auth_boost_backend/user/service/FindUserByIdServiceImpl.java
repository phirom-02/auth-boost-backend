package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.user.User;
import com.geekybyte.auth_boost_backend.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUserByIdServiceImpl implements FindUserByIdService {

    private final UserRepository userRepository;

    public FindUserByIdServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }
}
