package com.makar.test.service.impl;

import com.makar.test.config.TokenProvider;
import com.makar.test.config.UserPrincipal;
import com.makar.test.domain.Role;
import com.makar.test.domain.UserAuth;
import com.makar.test.domain.enums.Roles;
import com.makar.test.domain.request.AuthenticateRequest;
import com.makar.test.exception.NotFoundException;
import com.makar.test.repository.RoleRepository;
import com.makar.test.repository.UserAuthRepository;
import com.makar.test.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    private final UserAuthRepository userAuthRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    @Autowired
    public UserAuthServiceImpl(UserAuthRepository userAuthRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, AuthenticationManager authenticationManager, RoleRepository roleRepository) {
        this.userAuthRepository = userAuthRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }

    @Override
    public void register(AuthenticateRequest registerRequest) {
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        UserAuth userAuth = new UserAuth(encodedPassword, registerRequest.getLogin());

        Role role = roleRepository.findByName(Roles.ROLE_USER)
                .orElseThrow(() -> new NotFoundException("Role not found"));
        userAuth.setRoles(Collections.singletonList(role));
        userAuthRepository.save(userAuth);
    }

    @Override
    public String login(AuthenticateRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return tokenProvider.generateToken(authenticate);
    }

    @Override
    public UserAuth getCurrentUser() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userAuthRepository.findById(principal.getId())
                .orElseThrow(() -> new NotFoundException("no user in security context"));
    }

    @Override
    public UserAuth findByEmail(String email) {
        return userAuthRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("userAuth not found by email"));
    }

}
