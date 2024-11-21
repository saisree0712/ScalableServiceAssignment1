package com.FoodDelivery.AdminService.Controller;

import com.FoodDelivery.AdminService.model.AuthRequest;
import com.FoodDelivery.AdminService.model.UserDetails;
import com.FoodDelivery.AdminService.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Authenticates a user and generates a JWT token.
     *
     * @param authRequest The authentication request containing username and password.
     * @return ResponseEntity containing the JWT token.
     */
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody AuthRequest authRequest) {
        System.out.println("started");
        try {
            // Validate user and get user details
            UserDetails userDetails = authService.validateUser(authRequest);

            // Generate JWT token for the authenticated user
            String token = authService.generateToken(userDetails);

            return ResponseEntity.ok(token);  // Return the token in the response body
        } catch (Exception e) {
            // If authentication fails, return a 401 Unauthorized error
            return ResponseEntity.status(401).body("Invalid credentials: " + e.getMessage());
        }
    }

    /**
     * A secured endpoint that requires a valid JWT token to access.
     *
     * @param request The HTTP request containing the Authorization header with the token.
     * @return A message indicating successful access if the token is valid.
     */
    @GetMapping("/secured")
    public ResponseEntity<String> getSecuredResource(HttpServletRequest request) {
        String token = request.getHeader("Authorization");  // Retrieve the token from the Authorization header

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body("Authorization token missing or invalid.");
        }

        try {
            // Extract and validate the token
            String jwtToken = token.substring(7);  // Remove "Bearer " prefix
            String username = authService.parseToken(jwtToken);

            if (username != null) {
                return ResponseEntity.ok("Welcome " + username + "! You have accessed a secured resource.");
            } else {
                return ResponseEntity.status(401).body("Invalid or expired token.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid or expired token.");
        }
    }
}
