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
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Restaurant restaurant) {
        System.out.println("Received restaurant: " + restaurant.toString()); // Print the entire restaurant object

        if (restaurantDetailsService.existsByName(restaurant.getName())) {
            System.out.println("Restaurant with name " + restaurant.getName() + " already exists.");
            return ResponseEntity.badRequest().body("Restaurant with the same name already exists");
        }

        try {
            Restaurant savedRestaurant = restaurantRepository.save(restaurant);
            System.out.println("Saved restaurant: " + savedRestaurant.toString());

            return ResponseEntity.ok(new ResponseMessage("Restaurant created successfully"));
        } catch (Exception e) {
            System.err.println("Error creating restaurant: " + e.getMessage());
            e.printStackTrace(); // Print the full stack trace for detailed debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while creating restaurant: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(@RequestParam("q") String query) {
        try {
            List<Restaurant> restaurants = restaurantRepository.findByNameContainingIgnoreCase(query);
            return ResponseEntity.ok(restaurants);
        } catch (Exception e) {
            // Log the error
            System.err.println("Error during restaurant search: " + e.getMessage());
            // Return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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
