package unipi.p18023p16094.restaurantRatings_back.model;

import jakarta.persistence.*;


//@Entity
//@DiscriminatorValue("admin")  // Specify discriminator value for Admin
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    // Μέθοδος για προσθήκη εστιατορίου
    public void addRestaurant(String request) {
        // Υλοποίηση για προσθήκη εστιατορίου
    }

    // Μέθοδος για επεξεργασία εστιατορίου
    public void editRestaurant(String request, int id) {
        // Υλοποίηση για επεξεργασία εστιατορίου
    }

    // Μέθοδος για διαγραφή εστιατορίου
    public void deleteRestaurant(String request, int id) {
        // Υλοποίηση για διαγραφή εστιατορίου
    }

    // Μέθοδος για προσθήκη κριτικής
    public void addReview(String request, int id) {
        // Υλοποίηση για προσθήκη κριτικής
    }

    // Μέθοδος για επεξεργασία κριτικής
    public void editReview(String request, int restaurantId, int reviewId) {
        // Υλοποίηση για επεξεργασία κριτικής
    }

    // Μέθοδος για διαγραφή κριτικής
    public void deleteReview(String request, int restaurantId, int reviewId) {
        // Υλοποίηση για διαγραφή κριτικής
    }
}