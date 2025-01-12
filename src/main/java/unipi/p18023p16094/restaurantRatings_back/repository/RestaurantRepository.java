package unipi.p18023p16094.restaurantRatings_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unipi.p18023p16094.restaurantRatings_back.model.Restaurant;
import unipi.p18023p16094.restaurantRatings_back.model.User;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findByName(String name);
    boolean existsByName(String name); // Query by username instead of ID
    List<Restaurant> findByNameContainingIgnoreCase(String query);


}
