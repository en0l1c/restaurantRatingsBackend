//package unipi.p18023p16094.restaurantRatings_back.service;
//
//import unipi.p18023p16094.restaurantRatings_back.model.User;
//import unipi.p18023p16094.restaurantRatings_back.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public String register(User user) {
//        if (userRepository.findByUsername(user.getUsername()) != null) {
//            return "Username already exists!";
//        }
//        userRepository.save(user);
//        return "User registered successfully!";
//    }
//
//    public String login(User user) {
//        User existingUser = userRepository.findByUsername(user.getUsername());
//        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
//            return "Login successful!";
//        }
//        return "Invalid username or password!";
//    }
//}
//
