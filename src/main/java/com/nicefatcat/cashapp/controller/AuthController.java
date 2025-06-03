package com.nicefatcat.cashapp.controller;

import com.nicefatcat.cashapp.dto.LoginDto;
import com.nicefatcat.cashapp.dto.TokenResponseDto;
import com.nicefatcat.cashapp.dto.UserRegisterDto;
import com.nicefatcat.cashapp.entity.User;
import com.nicefatcat.cashapp.repository.UserRepository;
import com.nicefatcat.cashapp.security.JwtService;
import com.nicefatcat.cashapp.security.RefreshTokenStore;
import com.nicefatcat.cashapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final RefreshTokenStore refreshTokenStore;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto dto) {
        return ResponseEntity.ok(userService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginDto dto) {
        System.out.println("Login attempt: " + dto.getEmail());
        User user = userService.authenticate(dto);
        String access = jwtService.generateAccessToken(user);
        String refresh = jwtService.generateRefreshToken(user);
        refreshTokenStore.save(user.getEmail(), refresh);
        return ResponseEntity.ok(new TokenResponseDto(access, refresh));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDto> refresh(@RequestBody TokenResponseDto tokens) {
        String email = jwtService.extractUsername(tokens.getRefreshToken());
        if (!refreshTokenStore.validate(email, tokens.getRefreshToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = userRepository.findByEmail(email).orElseThrow();
        String newAccess = jwtService.generateAccessToken(user);
        String newRefresh = jwtService.generateRefreshToken(user);
        refreshTokenStore.save(email, newRefresh);
        return ResponseEntity.ok(new TokenResponseDto(newAccess, newRefresh));
    }
}
