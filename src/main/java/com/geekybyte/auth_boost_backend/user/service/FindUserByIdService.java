package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.user.User;

import java.util.Optional;

public interface FindUserByIdService {

    Optional<User> findUserById(int id);
}
