package com.loginService.controller;

import com.loginService.payload.JwtAuthenticationResponse;
import com.loginService.payload.LoginRequest;
import com.loginService.repository.UserRepository;
import com.loginService.security.JwtTokenProvider;
import com.loginService.security.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/login")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        JwtAuthenticationResponse authenticationResponse = null;
        
        if (jwt != null && !jwt.isEmpty()) {
        	authenticationResponse = new JwtAuthenticationResponse();
        	authenticationResponse.setMessage("Success login");
        	authenticationResponse.setValue("OK");
        	authenticationResponse.setId( String.valueOf(((UserPrincipal) authentication.getPrincipal()).getId())  );
        	authenticationResponse.setUser(((UserPrincipal) authentication.getPrincipal()).getUsername());
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", jwt);
        //ResponseEntity.o
        
        
        return ResponseEntity.ok().headers(responseHeaders).body(authenticationResponse);
    }
}
