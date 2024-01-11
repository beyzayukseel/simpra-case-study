package com.beyzanur.simpracasestudy.service;

import com.beyzanur.simpracasestudy.entity.User;
import com.beyzanur.simpracasestudy.model.LoginRequest;
import com.beyzanur.simpracasestudy.model.RegisterRequest;
import com.beyzanur.simpracasestudy.repository.UserRepository;
import com.beyzanur.simpracasestudy.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.email());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        return registerUser(user);
    }

    public String registerUser(User user) {
        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String authenticate(LoginRequest authenticationRequest) {
        // handle for error
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.username(), authenticationRequest.password()));
        var user = userRepository.findByEmail(authenticationRequest.username()).orElseThrow();
        return jwtService.generateToken(user);
    }
}
