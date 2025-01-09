package unipi.p18023p16094.restaurantRatings_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import unipi.p18023p16094.restaurantRatings_back.model.Restaurant;
import unipi.p18023p16094.restaurantRatings_back.model.User;
import unipi.p18023p16094.restaurantRatings_back.repository.RestaurantRepository;
import unipi.p18023p16094.restaurantRatings_back.service.JwtService;
import unipi.p18023p16094.restaurantRatings_back.service.RestaurantDetailsService;
import unipi.p18023p16094.restaurantRatings_back.service.RestaurantService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantDetailsService restaurantDetailsService;  // Ensure this is correctly autowired

//    @Autowired
//    private JwtService jwtService;
//
//    /****/
//    @CrossOrigin(origins = "http://localhost:4200")  // Enable CORS for the frontend
//    @GetMapping("/")
//    public ResponseEntity<List<User>> getAllUsers(@RequestHeader(value = "Authorization", required = false) String token) {
//        System.out.println("Authorization header: " + token);
//
//        if (token != null && token.startsWith("Bearer ")) {
//            String jwtToken = token.substring(7);
//            System.out.println("Token received: " + jwtToken);
//
//            try {
//                String username = jwtService.extractUsername(jwtToken);
//                if (username == null) {
//                    System.out.println("Invalid token: Username extraction failed.");
//                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//                }
//
//                Optional<User> authenticatedUser = Optional.ofNullable(userRepository.findByUsername(username));
//                if (authenticatedUser.isEmpty()) {
//                    System.out.println("User not found for username: " + username);
//                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//                }
//
//                List<User> users = userRepository.findAll();
//                return ResponseEntity.ok(users);
//            } catch (Exception e) {
//                System.out.println("Token validation error: " + e.getMessage());
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            }
//        }
//
//        System.out.println("Authorization header missing or invalid format.");
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }
//    /****/

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
//        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
//        return ResponseEntity.ok(restaurants);
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

//    @PostMapping("/")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
//        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedRestaurant);
//    }

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

//    // Optional: Endpoint to delete a restaurant
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
//        if (!restaurantRepository.existsById(id)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        restaurantRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
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


//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody User user) {
//        System.out.println("Register request received: " + user);
//
//        if (userRepository.existsByUsername(user.getUsername())) {
//            return ResponseEntity.badRequest().body("Username already exists");
//        }
//
//        try {
//            // Encode the password before saving
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            String encodedPassword = passwordEncoder.encode(user.getPassword());
//            user.setPassword(encodedPassword); // Set the encoded password
//
//            // Set the default role (1 - Simple User) if no role is provided (assuming role is not provided)
//            if (user.getRole() == null) {  // if role is not provided, it might be null
//                user.setRole(1); // Set default role as 1 (simple user)
//            }
//
//            userRepository.save(user); // Save user to the database
//            System.out.println("User saved successfully.");
//            return ResponseEntity.ok(new AuthController.ResponseMessage("User registered successfully"));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving user: " + e.getMessage());
//        }
//    }



//    @PutMapping("/auth/users/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
//        Optional<User> existingUser = userRepository.findById(id);
//        if (existingUser.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//        User user = existingUser.get();
//        user.setUsername(updatedUser.getUsername());
//        user.setEmail(updatedUser.getEmail());
//        user.setRole(updatedUser.getRole());
//
//        userRepository.save(user);
//        return ResponseEntity.ok(user);
//    }


}
