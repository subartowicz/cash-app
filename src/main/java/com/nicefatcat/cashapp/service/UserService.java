package com.nicefatcat.cashapp.service;

import com.nicefatcat.cashapp.dto.LoginDto;
import com.nicefatcat.cashapp.dto.UserRegisterDto;
import com.nicefatcat.cashapp.entity.User;

public interface UserService {
    User register(UserRegisterDto dto);
    User authenticate(LoginDto dto);
}
