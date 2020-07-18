package com.rubenskj.portfolio.controller;

import com.rubenskj.portfolio.security.dto.UserCreationDTO;
import com.rubenskj.portfolio.security.dto.UserDTO;
import com.rubenskj.portfolio.security.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('RL_ADMIN')")
    public UserDTO createUser(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        return UserDTO.of(this.userService.save(userCreationDTO));
    }

    @GetMapping("/{userId}")
    public UserDTO findById(@PathVariable("userId") String userId) {
        return UserDTO.of(this.userService.findById(userId));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('RL_ADMIN')")
    public void deleteById(@PathVariable("userId") String userId) {
        this.userService.deleteById(userId);
    }
}
