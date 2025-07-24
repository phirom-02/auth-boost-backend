package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.user.User;
import com.geekybyte.auth_boost_backend.user.dto.UpdateUserDto;

public interface UpdateUserService {

    User updateUser(int id, UpdateUserDto dto);
}
