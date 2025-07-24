package com.geekybyte.auth_boost_backend.user.dto;

import java.util.List;

public record UsersDto(List<UserDto> tenants) {
}
