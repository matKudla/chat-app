package com.kudla.chatappbackend.user.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationUserRequest {
    String username;
    String password;
}
