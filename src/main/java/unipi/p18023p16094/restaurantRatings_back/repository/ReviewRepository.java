package unipi.p18023p16094.restaurantRatings_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unipi.p18023p16094.restaurantRatings_back.model.Restaurant;
import unipi.p18023p16094.restaurantRatings_back.model.Review;
import unipi.p18023p16094.restaurantRatings_back.model.User;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRestaurantId(Long restaurantId);


}
