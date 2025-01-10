package unipi.p18023p16094.restaurantRatings_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipi.p18023p16094.restaurantRatings_back.model.Restaurant;
import unipi.p18023p16094.restaurantRatings_back.repository.RestaurantRepository;

import java.util.Optional;

@Service
public class RestaurantDetailsService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // A method to fetch restaurant by name, similar to how we fetch users by username
    public Restaurant getByName(String name) {
        return restaurantRepository.findByName(name);  // Assuming this method exists in the repository
    }

    // A method to check if a restaurant already exists
    public boolean existsByName(String name) {
        return restaurantRepository.existsByName(name);  // Assuming this method exists in the repository
    }

    public Optional<Restaurant> findById(Long id) {
        return restaurantRepository.findById(id);
    }
}
