package com.prospecta.challange.controller;

import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.prospecta.challange.configs.JwtToken;
import com.prospecta.challange.configs.SecurityDetails;
import com.prospecta.challange.model.LoginRequest;
import com.prospecta.challange.model.Users;
import com.prospecta.challange.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUserHandler(@RequestBody Users user) throws Exception {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }else {
        	Users saveduser= userService.saveUser(user);    	
        	return new ResponseEntity<>(saveduser, HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> logInUserHandler(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            String jwt = JwtToken.generateToken(loginRequest.getUsername(), authentication.getAuthorities());

            // Create a map to hold the token
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("token", jwt);

            // Return the token in the response body and header
            return ResponseEntity.ok()
                    .header(SecurityDetails.JWT_HEADER, "Bearer " + jwt)
                    .body(responseBody);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    
    
    @PostMapping("/logout")
    public ResponseEntity<?> logOutUserHandler(HttpServletResponse response) {
        response.setHeader(SecurityDetails.JWT_HEADER, null);
        return ResponseEntity.ok("Logout successful");
    }

}

