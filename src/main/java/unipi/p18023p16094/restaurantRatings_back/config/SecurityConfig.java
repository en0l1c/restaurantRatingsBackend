package unipi.p18023p16094.restaurantRatings_back.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;
import unipi.p18023p16094.restaurantRatings_back.service.JwtService;

import java.io.IOException;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**", "/restaurants/**").permitAll() // Allow all under /auth/
                .requestMatchers("/restaurants/all").permitAll() // Allow public access to get all restaurants
                .requestMatchers("/restaurants/**").permitAll() // Allow public access to get all restaurants
                .anyRequest().authenticated(); // Protect all other endpoints
        return http.build();
    }



//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("http://localhost:4200");  // Your frontend URL
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedMethod("*");
//        configuration.addExposedHeader("Authorization");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}

