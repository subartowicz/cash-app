package com.nicefatcat.cashapp.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
