package unipi.p18023p16094.restaurantRatings_back.model;

import jakarta.persistence.*;


//@Entity
//@DiscriminatorValue("guest")  // Specify discriminator value for Guest
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Μέθοδος για την εγγραφή χρήστη
    public void register(String request) {
        // Υλοποίηση για την εγγραφή χρήστη
    }
}
