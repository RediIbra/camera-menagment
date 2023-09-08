package com.npa.getway.dto;

import lombok.Data;

@Data
public class UsersDTO {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private boolean isEnabled;
}
