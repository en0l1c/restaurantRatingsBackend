package unipi.p18023p16094.restaurantRatings_back.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    // Use a secure key for signing JWTs
    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Secure key for HS256

    // Method to create a JWT token for the user
    public String createToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                .signWith(secretKey) // Use the secure key for signing
                .compact();
    }

//    // Overloaded method that can accept user details as a User object
//    public String createToken(User user) {
//        return createToken((UserDetails) user);  // Reuse the UserDetails-based method
//    }

    // Method to generate a JWT token with additional claims
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createTokenWithClaims(claims, userDetails.getUsername());
    }


//    // Method to generate a JWT token with additional claims (like role)
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        String username = userDetails.getUsername();
//
//        // Extract user role from UserDetails (make sure UserDetails includes role)
//        String role = ((User) userDetails).getAuthorities().stream()
//                .findFirst()  // Assuming role is the first authority
//                .map(authority -> authority.getAuthority())
//                .orElse("ROLE_USER"); // Default to "ROLE_USER" if no role is found
//
//        // Add the role to the claims
//        claims.put("role", role);  // Store role in the claims
//
//        return createTokenWithClaims(claims, username);
//    }

    // Helper method to create token with custom claims
    private String createTokenWithClaims(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                .signWith(secretKey) // Use the secure key for signing
                .compact();
    }

    // Validate the token and check if it belongs to the given user
    public boolean isValidToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }


    // Extract the username from the token
//    public String extractUsername(String token) {
//        return Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }

    // Check if the token has expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extract the expiration date from the token
    private Date extractExpiration(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }



    // for user controller
    public boolean isTokenValid(String token) {
        try {
            final String username = extractUsername(token);
            return username != null && !isTokenExpired(token);
        } catch (Exception e) {
            System.out.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }
//    public String extractUsername(String token) {
//        try {
//            // Parse the token
//            return Jwts.parser()
//                    .setSigningKey(secretKey)
//                    .parseClaimsJws(token)
//                    .getBody()
//                    .getSubject();
//        } catch (Exception e) {
//            System.out.println("Failed to extract username from token: " + e.getMessage());
//            return null;
//        }
//    }

    public String extractUsername(String token) {
        try {
            // Extract claims
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey) // Replace with your secret key
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject(); // Usually the username
        } catch (Exception e) {
            System.out.println("Error extracting username: " + e.getMessage());
            return null;
        }
    }


}
























































