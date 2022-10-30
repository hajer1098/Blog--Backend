package com.example.Blog.security;

import com.example.Blog.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.KeyStore;
import java.util.Date;

@Service
public class JwtProvider {
    private Key key;
    private String secret;
    @PostConstruct
    public void init(){



    }

    public String generateToken(Authentication authentication){
        User principal = (User) authentication.getPrincipal();


        return Jwts.builder()
                .setSubject(principal.getUserName())
                .signWith(SignatureAlgorithm.HS512, secret).compact();







    }
}
