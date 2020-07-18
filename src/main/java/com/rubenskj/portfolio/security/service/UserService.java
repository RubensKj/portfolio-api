package com.rubenskj.portfolio.security.service;

import com.rubenskj.portfolio.exception.NotFoundException;
import com.rubenskj.portfolio.security.dto.UserCreationDTO;
import com.rubenskj.portfolio.security.model.User;
import com.rubenskj.portfolio.security.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(UserCreationDTO userCreationDTO) {
        LOGGER.info("Creating user.");

        User userFromDTO = this.createUserFromDTO(userCreationDTO);

        return this.userRepository.save(userFromDTO);
    }

    private User createUserFromDTO(UserCreationDTO userCreationDTO) {
        return new User(
                userCreationDTO.getEmail(),
                passwordEncoder.encode(userCreationDTO.getPassword()),
                userCreationDTO.getAuthorities(),
                true
        );
    }

    public User findById(String userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with this id. Id: " + userId));
    }

    public boolean existsById(String userId) {
        return this.userRepository.existsById(userId);
    }

    public void deleteById(String userId) {
        if (!this.existsById(userId)) {
            throw new NotFoundException("User not found with this id, so cannot be deleted. Id: " + userId);
        }

        this.userRepository.deleteById(userId);
    }

    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public void deleteUserByEmail(String email) {
        if (!existsByEmail(email)) {
            throw new NotFoundException("User not found with this e-mail, so cannot be deleted. E-mail: " + email);
        }

        this.userRepository.deleteByEmail(email);
    }
}
