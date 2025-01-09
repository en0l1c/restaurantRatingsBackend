package unipi.p18023p16094.restaurantRatings_back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

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

}




//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.cors() // Enable CORS settings from WebConfig
//                .and()
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/auth/**", "/restaurants/create").permitAll() // Allow all under /auth/
//                .anyRequest().authenticated(); // Protect all other endpoints
//        return http.build();
//    }
//}
