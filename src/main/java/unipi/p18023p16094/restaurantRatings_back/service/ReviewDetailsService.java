package unipi.p18023p16094.restaurantRatings_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipi.p18023p16094.restaurantRatings_back.model.Restaurant;
import unipi.p18023p16094.restaurantRatings_back.model.Review;
//import unipi.p18023p16094.restaurantRatings_back.repository.RestaurantRepository;
import unipi.p18023p16094.restaurantRatings_back.model.User;
import unipi.p18023p16094.restaurantRatings_back.repository.RestaurantRepository;
import unipi.p18023p16094.restaurantRatings_back.repository.ReviewRepository;
import unipi.p18023p16094.restaurantRatings_back.repository.UserRepository;

@Service
public class ReviewDetailsService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;



    public boolean existsById(Long id) {
//        if (id == null) {
//            throw new IllegalArgumentException("ID cannot be null");
//        }
        return reviewRepository.existsById(id);
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }


}
