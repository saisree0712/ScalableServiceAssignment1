package com.FoodDelivery.AdminService.service;

import com.FoodDelivery.AdminService.model.AuthRequest;
import com.FoodDelivery.AdminService.model.UserDetails;
import com.FoodDelivery.AdminService.model.User;
import com.FoodDelivery.AdminService.Repository.UserRepository;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private String secretKey = "mySecretKey67576URUYYS749J578243BJHhmySecretKey67576URUYYS749J578243BJHhgmySecretKey67576URUYYS749J578243BJHhgg=";
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // Validate the user credentials and generate JWT token
    public UserDetails validateUser(AuthRequest authRequest) throws Exception {
        List<User> userList = userRepository.findByUsername(authRequest.getUserName());

        if (userList.isEmpty()) {
            throw new Exception("User not found.");
        }

        User user = userList.get(0);
        if (!authRequest.getPassword().equals(user.getPassword()) ) {
            throw new Exception("Invalid credentials");
        }

        return new UserDetails(user.getUsername(), user.getPassword(), new ArrayList<>(Collections.singleton("Admin")));
    }

    // Generate JWT token for the authenticated user
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    // Parse JWT token and retrieve username (or other details)
    public String parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();  // Extract the username (subject)
    }
}
