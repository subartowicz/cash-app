package com.nicefatcat.cashapp.security;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RefreshTokenStore {
    private final Map<String, String> store = new ConcurrentHashMap<>();

    public void save(String email, String refreshToken) {
        store.put(email, refreshToken);
    }

    public boolean validate(String email, String refreshToken) {
        return refreshToken.equals(store.get(email));
    }
}
