package com.rubenskj.portfolio.controller;

import com.rubenskj.portfolio.security.dto.AuthDTO;
import com.rubenskj.portfolio.security.dto.LoginDTO;
import com.rubenskj.portfolio.security.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthDTO login(@Valid @RequestBody LoginDTO loginDTO) {
        return this.authService.authenticate(loginDTO);
    }
}
