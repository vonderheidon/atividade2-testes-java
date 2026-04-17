package com.app.auth.dto;

import com.app.auth.model.User;

import java.util.UUID;

public class UserResponseDTO {
    private UUID id;
    private String name;
    private String email;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}