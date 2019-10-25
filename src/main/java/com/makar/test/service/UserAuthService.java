package com.makar.test.service;

import com.makar.test.domain.UserAuth;
import com.makar.test.domain.request.AuthenticateRequest;

public interface UserAuthService {

    void register(AuthenticateRequest registerRequest);

    String login(AuthenticateRequest loginRequest);

    UserAuth getCurrentUser();

    UserAuth findByEmail(String email);

}
