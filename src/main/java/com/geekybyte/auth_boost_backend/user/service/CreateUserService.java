package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.user.User;
import com.geekybyte.auth_boost_backend.user.dto.CreateUserDto;

public interface CreateUserService {

    User createUser(CreateUserDto dto);
}
