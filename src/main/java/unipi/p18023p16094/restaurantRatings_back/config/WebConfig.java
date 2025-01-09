package unipi.p18023p16094.restaurantRatings_back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(true)
                .exposedHeaders("Authorization");
    }
}


//package unipi.p18023p16094.restaurantRatings_back.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // Allow all endpoints
//                .allowedOrigins("http://localhost:4200") // Allow Angular frontend
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow these methods
//                .allowCredentials(true) // Allow credentials (cookies, JWT tokens, etc.)
//                .allowedHeaders("*");  // Allow all headers
//    }
//}




//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // Allow all endpoints
//                .allowedOrigins("http://localhost:4200") // Allow Angular frontend
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow these methods
//                .allowCredentials(true) // Allow credentials (cookies, JWT tokens, etc.)
//                .allowedHeaders("Authorization", "*");  // Allow all headers (including Authorization)
//    }
//}