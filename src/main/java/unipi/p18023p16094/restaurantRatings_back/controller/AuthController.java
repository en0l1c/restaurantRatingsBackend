package unipi.p18023p16094.restaurantRatings_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import unipi.p18023p16094.restaurantRatings_back.model.User;
import unipi.p18023p16094.restaurantRatings_back.repository.UserRepository;
import unipi.p18023p16094.restaurantRatings_back.service.JwtService;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
//        );
//
//        // Extract UserDetails from the authentication object
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//        // Generate JWT token
//        String jwtToken = jwtService.generateToken(userDetails);
//
//        return ResponseEntity.ok(jwtToken); // Send token back to the client
//    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        // Extract UserDetails from the authentication object
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Generate JWT token
        String jwtToken = jwtService.generateToken(userDetails);

        // Send token in JSON response
        return ResponseEntity.ok(new JwtResponse(jwtToken)); // Return JWT in response body
    }

    // JwtResponse class
    public static class JwtResponse {
        private String token;

        public JwtResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }



//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody User user) {
//        System.out.println("Register request received: " + user);
//
//        if (userRepository.existsByUsername(user.getUsername())) {
//            return ResponseEntity.badRequest().body("Username already exists");
//        }
//
//        try {
//            // Encode the password before saving
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            String encodedPassword = passwordEncoder.encode(user.getPassword());
//            user.setPassword(encodedPassword); // Set the encoded password
//
//            userRepository.save(user); // Save user to the database
//            System.out.println("User saved successfully.");
//            return ResponseEntity.ok(new ResponseMessage("User registered successfully"));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving user: " + e.getMessage());
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        System.out.println("Register request received: " + user);

        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        try {
            // Encode the password before saving
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword); // Set the encoded password

            // Set the default role (1 - Simple User) if no role is provided (assuming role is not provided)
            if (user.getRole() == null) {  // if role is not provided, it might be null
                user.setRole(1); // Set default role as 1 (simple user)
            }

            userRepository.save(user); // Save user to the database
            System.out.println("User saved successfully.");
            return ResponseEntity.ok(new ResponseMessage("User registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving user: " + e.getMessage());
        }
    }


//    @GetMapping("/user")
//    public ResponseEntity<User> getUserDetails(@RequestHeader("Authorization") String token) {
//        if (token != null && token.startsWith("Bearer ")) {
//            String jwtToken = token.substring(7); // Extract the JWT token part
//            System.out.println("JWT Token: " + jwtToken); // Debug: print the token
//            String username = jwtService.extractUsername(jwtToken);  // Extract username from JWT
//            System.out.println("Extracted Username: " + username); // Debug: print the username
//
//            Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
//            if (user.isPresent()) {
//                return ResponseEntity.ok(user.get());
//            }
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }

    // ResponseMessage class:
    public class ResponseMessage {
        private String message;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


}


