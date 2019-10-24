package com.makar.test.controller;

import com.makar.test.domain.request.AuthenticateRequest;
import com.makar.test.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/auth")
public class UserAuthController {

    private final UserAuthService userAuthService;

    @Autowired
    public UserAuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PostMapping("register")
    public void register(@RequestBody AuthenticateRequest registerRequest) {
        userAuthService.register(registerRequest);
    }

    @PostMapping("login")
    public String login(@RequestBody AuthenticateRequest authenticateRequest) {
        return userAuthService.login(authenticateRequest);
    }

    // todo add for test, remove later
    @GetMapping("test")
    @PreAuthorize("hasRole('USER')")
    public String test() {
        return "user role is working";
    }

}

