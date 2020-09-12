package com.rubenskj.portfolio.security.configuration;

import com.rubenskj.portfolio.security.service.UserDetailsServiceImpl;
import com.rubenskj.portfolio.security.uuid.UuidAuthEntryPoint;
import com.rubenskj.portfolio.security.uuid.UuidAuthFilter;
import com.rubenskj.portfolio.security.uuid.UuidProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true
)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final UuidAuthEntryPoint uuidAuthEntryPoint;
    private final UuidProvider uuidProvider;

    public WebSecurityConfiguration(UserDetailsServiceImpl userDetailsService, UuidAuthEntryPoint uuidAuthEntryPoint, UuidProvider uuidProvider) {
        this.userDetailsService = userDetailsService;
        this.uuidAuthEntryPoint = uuidAuthEntryPoint;
        this.uuidProvider = uuidProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(this.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requiresChannel().anyRequest().requiresSecure().and()
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("*").permitAll()
                .and().exceptionHandling().authenticationEntryPoint(uuidAuthEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationUuidAuthFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public UuidAuthFilter authenticationUuidAuthFilter() {
        return new UuidAuthFilter(uuidProvider, userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
