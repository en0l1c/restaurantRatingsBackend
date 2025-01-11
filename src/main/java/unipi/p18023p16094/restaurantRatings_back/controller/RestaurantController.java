package unipi.p18023p16094.restaurantRatings_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import unipi.p18023p16094.restaurantRatings_back.model.Restaurant;
import unipi.p18023p16094.restaurantRatings_back.model.Review;
import unipi.p18023p16094.restaurantRatings_back.model.User;
import unipi.p18023p16094.restaurantRatings_back.repository.RestaurantRepository;
import unipi.p18023p16094.restaurantRatings_back.repository.ReviewRepository;
import unipi.p18023p16094.restaurantRatings_back.repository.UserRepository;
import unipi.p18023p16094.restaurantRatings_back.service.JwtService;
import unipi.p18023p16094.restaurantRatings_back.service.RestaurantDetailsService;
import unipi.p18023p16094.restaurantRatings_back.service.ReviewDetailsService;
//import unipi.p18023p16094.restaurantRatings_back.service.RestaurantService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantDetailsService restaurantDetailsService;

    @Autowired
    private ReviewDetailsService reviewDetailsService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;


//    private final RestaurantService restaurantService;
//
//    public RestaurantController(RestaurantService restaurantService) {
//        this.restaurantService = restaurantService;
//    }

    // Endpoint to get a list of all restaurants
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return ResponseEntity.ok(restaurants);
    }

    // Endpoint to get restaurant details by ID
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantDetails(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Optional: Endpoint to add a new restaurant (if you want to allow adding restaurants)
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRestaurant);
    }

    // Optional: Endpoint to update restaurant details
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant) {
        if (!restaurantRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        updatedRestaurant.setId(id); // Ensure ID is set
        restaurantRepository.save(updatedRestaurant);
        return ResponseEntity.ok(updatedRestaurant);
    }

// Optional: Endpoint to delete a restaurant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        restaurantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    // Endpoint for creating a restaurant
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Restaurant restaurant) {
        System.out.println("Restaurant creation request received: " + restaurant);


        // Check if the restaurant already exists by name
        if (restaurantDetailsService.existsByName(restaurant.getName())) {
            return ResponseEntity.badRequest().body("Restaurant with the same name already exists");
        }

        try {
            // Save the new restaurant to the database
            restaurantRepository.save(restaurant);
            System.out.println("Restaurant created successfully");
            return ResponseEntity.ok(new ResponseMessage("Restaurant created successfully"));
        } catch (Exception e) {
            // Handle any errors during restaurant creation
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating restaurant: " + e.getMessage());
        }
    }

    // post a new review
    @CrossOrigin(origins = "http://localhost:4200")  // Enable CORS for the frontend
    @PostMapping("/{restaurantId}/reviews/")
    public ResponseEntity<Review> createReview(@RequestBody Review review, @PathVariable Long restaurantId, @RequestHeader("Authorization") String token) {
        try {
            System.out.println("Authorization Header: " + token);  // Add this line to debug

            // Check if the token starts with "Bearer "
            if (token == null || !token.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            String jwtToken = token.substring(7);  // Remove "Bearer " prefix

            // Validate the token using JwtService
            if (!jwtService.isTokenValid(jwtToken)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // Invalid token
            }

            // Extract the username from the JWT token
            String username = jwtService.extractUsername(jwtToken);
            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // Token username extraction failed
            }

            Optional<User> authenticatedUser = Optional.ofNullable(userRepository.findByUsername(username));
            if (authenticatedUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // User not found
            }

            // Check if the user has already reviewed this restaurant
            Long userId = authenticatedUser.get().getId();
            boolean hasAlreadyReviewed = reviewRepository.existsByUserIdAndRestaurantId(userId, restaurantId);
            if (hasAlreadyReviewed) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);  // User has already reviewed this restaurant
            }

            // Associate the authenticated user with the review
            review.setUser(authenticatedUser.get());

            // Find the restaurant by ID and associate it with the review
            review.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found")));

            // Save the review
            Review savedReview = reviewRepository.save(review);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
        } catch (Exception e) {
            System.out.println("Error creating review: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // get reviews
    @GetMapping("/{restaurantId}/reviews")
    public ResponseEntity<List<Review>> getReviewsForRestaurant(@PathVariable Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId);
        return ResponseEntity.ok(reviews);
    }

    // Endpoint to update a review
    @PutMapping("/{restaurantId}/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long restaurantId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review updatedReview,
                                               @RequestHeader("Authorization") String token) {
        try {
            // Validate the token
            if (token == null || !token.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            String jwtToken = token.substring(7);  // Remove "Bearer " prefix
            if (!jwtService.isTokenValid(jwtToken)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            // Extract username from JWT token
            String username = jwtService.extractUsername(jwtToken);
            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Optional<User> authenticatedUser = Optional.ofNullable(userRepository.findByUsername(username));
            if (authenticatedUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Long userId = authenticatedUser.get().getId();

            // Check if the review exists and belongs to the current user
            Optional<Review> review = reviewRepository.findById(reviewId);
            if (review.isEmpty() || !review.get().getUser().getId().equals(userId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();  // User is not the owner of the review
            }

            // Update the review
            Review existingReview = review.get();
            existingReview.setRating(updatedReview.getRating());
            existingReview.setComment(updatedReview.getComment());
            reviewRepository.save(existingReview);

            return ResponseEntity.ok(existingReview);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    // ResponseMessage class:
    public class ResponseMessage {
        private String message;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }



}
