package unipi.p18023p16094.restaurantRatings_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
//import unipi.p18023p16094.restaurantRatings_back.model.User;
import unipi.p18023p16094.restaurantRatings_back.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;  // Use your own user repository to fetch user

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        unipi.p18023p16094.restaurantRatings_back.model.User user = userRepository.findByUsername(username);  // Replace with your user fetching logic
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // You can customize UserDetails with roles or authorities if needed
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())  // Assuming you store hashed passwords
                .authorities("USER")  // Or your custom roles/authorities
                .build();
    }
}
