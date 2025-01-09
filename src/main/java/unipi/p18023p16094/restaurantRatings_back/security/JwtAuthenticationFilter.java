//package unipi.p18023p16094.restaurantRatings_back.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import unipi.p18023p16094.restaurantRatings_back.service.JwtService;
//import unipi.p18023p16094.restaurantRatings_back.service.MyUserDetailsService;
//
//import java.io.IOException;
//import java.util.Collections;
//
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtService jwtService;
//
//    public JwtAuthenticationFilter(JwtService jwtService) {
//        this.jwtService = jwtService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String token = extractTokenFromHeader(request);
//
//        if (token != null && jwtService.isTokenValid(token)) {
//            Authentication authentication = new UsernamePasswordAuthenticationToken(
//                    jwtService.extractUsername(token), null, Collections.emptyList());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String extractTokenFromHeader(HttpServletRequest request) {
//        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (header != null && header.startsWith("Bearer ")) {
//            return header.substring(7);
//        }
//        return null;
//    }
//}
