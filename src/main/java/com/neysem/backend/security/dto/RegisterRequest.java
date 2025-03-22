package com.neysem.backend.security.dto;

import com.neysem.backend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private Role role;
}

