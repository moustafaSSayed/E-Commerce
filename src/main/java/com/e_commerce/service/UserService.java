package com.e_commerce.service;

import com.e_commerce.model.dto.UserDto;

public interface UserService {

    public String register(UserDto userDto);

    public String login(UserDto userDto);
}
