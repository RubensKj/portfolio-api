package com.rubenskj.portfolio.security.service;

import com.rubenskj.portfolio.security.details.UserDetailsImpl;
import com.rubenskj.portfolio.security.model.User;
import com.rubenskj.portfolio.security.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;

    public UserDetailsServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Sorry, user not found with this e-mail. E-mail: " + email));

        return UserDetailsImpl.build(user);
    }
}
