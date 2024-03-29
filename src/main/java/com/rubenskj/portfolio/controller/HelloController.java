package com.rubenskj.portfolio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    // Rest Controller created to test CICD working..

    @GetMapping
    public String showCICDWorking() {
        return "Hello there, this is RubensKj's API";       
    }
}
