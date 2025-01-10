package unipi.p18023p16094.restaurantRatings_back.repository;

import unipi.p18023p16094.restaurantRatings_back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username); // Query by username instead of ID

}