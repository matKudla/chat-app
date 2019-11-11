package com.kudla.chatappbackend.user.model.Dto;

import lombok.Data;

@Data
public class AppUserDto {
    Long id;
    String firstName;
    String lastname;
    String username;
    String token;
}
