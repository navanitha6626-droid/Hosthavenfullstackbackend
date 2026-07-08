package com.example.hosthaven2.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hosthaven2.entity.SystemUser;
import com.example.hosthaven2.services.AuthService;
import com.example.hosthaven2.services.JwtService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    public AuthController(JwtService jwtService,AuthenticationManager authenticationManager,AuthService authService){
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    @PostMapping("/register")
     public ResponseEntity<?> register(@Valid @RequestBody SystemUser user) {
        try{
            SystemUser users=authService.register(user);
            return ResponseEntity.ok(users);
        }
        catch(Exception e)
        {
            Map<String,String> errstmt=new HashMap<>();
            if(e.getMessage()!=null  &&  e.getMessage().toLowerCase().contains("duplicate")){
                errstmt.put("message","Email id already registered");
            }
            else
            {
                errstmt.put("message", "Registration failed!."+e.getMessage());
            }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errstmt);
        }
    }


    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody SystemUser user) {
      try {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                ));

        String token = jwtService.generateToken(user.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login Successful");
        response.put("token", token);

        return ResponseEntity.ok(response);

    } catch (BadCredentialsException e) {

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Invalid Email or Password");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }
}
   
}
