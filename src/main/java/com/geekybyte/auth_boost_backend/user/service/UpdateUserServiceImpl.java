package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.user.User;
import com.geekybyte.auth_boost_backend.user.UserRepository;
import com.geekybyte.auth_boost_backend.user.dto.UpdateUserDto;
import com.geekybyte.auth_boost_backend.user.mapper.UpdateUserDtoToUser;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserServiceImpl implements UpdateUserService {

    private final UserRepository userRepository;
    private final FindUserByIdService findUserByIdService;

    public UpdateUserServiceImpl(
            UserRepository userRepository,
            FindUserByIdService findUserByIdService
    ) {
        this.userRepository = userRepository;
        this.findUserByIdService = findUserByIdService;
    }

    @Override
    public User updateUser(int id, UpdateUserDto dto) {
        User user = findUserByIdService.findUserById(id).orElse(null);

        if (user == null) {
            return null;
        }

        User updateUser = UpdateUserDtoToUser.map(user, dto);
        return userRepository.save(updateUser);
    }
}
