package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserByIdServiceImpl implements DeleteUserByIdService {

    private final UserRepository userRepository;

    public DeleteUserByIdServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
