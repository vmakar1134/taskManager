package com.makar.test.config;

import com.makar.test.domain.UserAuth;
import com.makar.test.exception.NotFoundException;
import com.makar.test.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAuthRepository userAuthRepository;

    @Autowired
    public CustomUserDetailsService(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserAuth> user = userAuthRepository.findByEmail(s);

        return user.map(UserPrincipal::create)
                .orElseThrow(() -> new NotFoundException("User not found by login"));
    }

    public UserDetails loadUserById(Long id) {
        return userAuthRepository.findById(id)
                .map(UserPrincipal::create)
                .orElseThrow(() -> new NotFoundException("User not found by id"));
    }

}
