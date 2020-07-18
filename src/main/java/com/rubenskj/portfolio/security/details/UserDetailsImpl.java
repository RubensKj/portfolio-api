package com.rubenskj.portfolio.security.details;

import com.rubenskj.portfolio.security.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private String id;
    private String email;
    private String password;
    private List<String> authorities;
    private boolean isEnabled;

    public UserDetailsImpl() {
    }

    public UserDetailsImpl(String id, String email, String password, List<String> authorities, boolean isEnabled) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.isEnabled = isEnabled;
    }

    public static UserDetails build(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities(),
                user.isEnabled()
        );
    }

    public static UserDetailsImpl getInstanceByAuthentication(Authentication authentication) {
        return (UserDetailsImpl) authentication.getPrincipal();
    }

    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public List<String> getAuthoritiesInString() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
