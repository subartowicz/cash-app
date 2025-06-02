package com.nicefatcat.cashapp.service;

import com.nicefatcat.cashapp.dto.LoginDto;
import com.nicefatcat.cashapp.dto.UserRegisterDto;
import com.nicefatcat.cashapp.entity.Role;
import com.nicefatcat.cashapp.entity.User;
import com.nicefatcat.cashapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(UserRegisterDto dto) {
        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.USER)
                .build();
        return userRepository.save(user);
    }

    @Override
    public User authenticate(LoginDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return user;
    }
}
