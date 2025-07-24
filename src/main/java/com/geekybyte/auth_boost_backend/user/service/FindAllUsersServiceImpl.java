package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.user.User;
import com.geekybyte.auth_boost_backend.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllUsersServiceImpl implements FindAllUsersService {

    private final UserRepository userRepository;

    public FindAllUsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
