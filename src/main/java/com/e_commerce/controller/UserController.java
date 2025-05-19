package com.e_commerce.controller;

import com.e_commerce.model.dto.UserDto;
import com.e_commerce.payload.AuthResponse;
import com.e_commerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDto user) {
        String token = userService.register(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserDto userDto) {
        String token = userService.login(userDto);
        return ResponseEntity.ok(new AuthResponse(token));
    }

}
