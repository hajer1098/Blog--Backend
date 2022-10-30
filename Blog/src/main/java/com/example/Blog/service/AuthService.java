package com.example.Blog.service;

import com.example.Blog.dto.LoginRequest;
import com.example.Blog.dto.RegisterRequest;
import com.example.Blog.models.User;
import com.example.Blog.repository.UserRepository;
import com.example.Blog.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    public void signup(RegisterRequest registerRequest) {
        User user=new User();

        user.setUserName(registerRequest.getUsername());
        user.setPassword(encodepassword(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());

        userRepository.save(user);

    }

    private String encodepassword(String password) {
         return passwordEncoder.encode(password);
    }

    public String login(LoginRequest loginRequest) {
        //entry point
       Authentication authentificate= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentificate);

       return jwtProvider.generateToken(authentificate);

    }
}
