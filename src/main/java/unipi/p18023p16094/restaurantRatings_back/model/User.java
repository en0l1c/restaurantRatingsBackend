package unipi.p18023p16094.restaurantRatings_back.model;

import jakarta.persistence.*;


@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users")
//@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class User extends Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private Integer role; // int to Integer to make null check

    // Μέθοδος για login χρήστη
    public void loginUser(String request) {
        // Υλοποίηση για login
    }

    // Μέθοδος για logout χρήστη
    public void logoutUser(String request) {
        // Υλοποίηση για logout
    }

    // Μέθοδος για να προσθέσει κριτική
    public void addReview(String request, int restaurantId) {
        // Υλοποίηση για προσθήκη κριτικής
    }

    // Μέθοδος για να επεξεργαστεί κριτική
    public void editReview(String request, int restaurantId, int reviewId) {
        // Υλοποίηση για επεξεργασία κριτικής
    }

    // Μέθοδος για να διαγράψει κριτική
    public void deleteReview(String request, int restaurantId, int reviewId) {
        // Υλοποίηση για διαγραφή κριτικής
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
