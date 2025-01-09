//package unipi.p18023p16094.restaurantRatings_back.security;
//
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.Claims;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import unipi.p18023p16094.restaurantRatings_back.service.JwtService;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    private static final String SECRET_KEY = "secret_key"; // Make sure to use a secure key
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
//        String jwtToken = getJwtFromRequest(request);
//
//        if (jwtToken != null && validateToken(jwtToken)) {
//            String username = extractUsername(jwtToken);
//
//            if (username != null) {
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//
//                // Set authentication details
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                // Set the authentication in the SecurityContext
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
//
//    private String getJwtFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//
//    private String extractUsername(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(SECRET_KEY)  // Set the key used to sign the token
//                .build()
//                .parseClaimsJws(token)   // Parse the JWT token
//                .getBody();
//        return claims.getSubject();
//    }
//
//    private boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(SECRET_KEY)  // Set the key used to sign the token
//                    .build()
//                    .parseClaimsJws(token);  // Validate the token
//            return true;
//        } catch (Exception e) {
//            throw new AuthenticationServiceException("Invalid or expired JWT token");
//        }
//    }
//}
