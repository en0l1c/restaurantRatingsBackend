package unipi.p18023p16094.restaurantRatings_back.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unipi.p18023p16094.restaurantRatings_back.model.User;
import unipi.p18023p16094.restaurantRatings_back.repository.UserRepository;
import unipi.p18023p16094.restaurantRatings_back.service.JwtService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:4200")  // Enable CORS for the frontend
    @GetMapping("/auth/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader(value = "Authorization", required = false) String token) {
        System.out.println("Authorization header: " + token);

        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            System.out.println("Token received: " + jwtToken);

            try {
                String username = jwtService.extractUsername(jwtToken);
                if (username == null) {
                    System.out.println("Invalid token: Username extraction failed.");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                Optional<User> authenticatedUser = Optional.ofNullable(userRepository.findByUsername(username));
                if (authenticatedUser.isEmpty()) {
                    System.out.println("User not found for username: " + username);
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                List<User> users = userRepository.findAll();
                return ResponseEntity.ok(users);
            } catch (Exception e) {
                System.out.println("Token validation error: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }

        System.out.println("Authorization header missing or invalid format.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @PutMapping("/auth/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        User user = existingUser.get();
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setRole(updatedUser.getRole());

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/auth/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Return 404 if user not found
        }

        userRepository.deleteById(id);  // Delete user by ID
        return ResponseEntity.noContent().build();  // Return 204 No Content on success
    }



}
