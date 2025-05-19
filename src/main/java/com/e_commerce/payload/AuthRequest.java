package com.e_commerce.payload;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
