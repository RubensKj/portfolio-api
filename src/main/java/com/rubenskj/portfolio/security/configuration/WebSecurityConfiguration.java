//package com.rubenskj.portfolio.security.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(
//        prePostEnabled = true,
//        securedEnabled = true
//)
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                .authorizeRequests()
//                .antMatchers("*").permitAll();
////                .and().exceptionHandling().authenticationEntryPoint(uuidAuthEntryPoint).and()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////
////        http.addFilterBefore(uuidAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}
