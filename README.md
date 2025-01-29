# Restaurant Ratings Backend

## Description
The **Restaurant Ratings Backend** is a RESTful API built with **Spring Boot** that allows users to register, log in, rate restaurants, and manage their reviews. Admins have the ability to hide reviews.

## Technologies Used
- **Java 23**
- **Spring Boot**
- **Spring Security (JWT Authentication)**
- **Hibernate (JPA)**
- **MySQL**
- **Maven**

## Installation & Running

### Prerequisites
- **Java 23+**
- **Maven**

### Run with Maven
```bash
mvn spring-boot:run
```

The backend will run at **[http://localhost:8080](http://localhost:8080)**

## REST API Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/auth/login` | User login |
| POST | `/auth/register` | Register a new user |

### Restaurants
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET | `/restaurants/all` | List all restaurants |
| GET | `/restaurants/{id}` | Get restaurant details |
| POST | `/restaurants/create` | Create a new restaurant |
| PUT | `/restaurants/{id}` | Update a restaurant |
| DELETE | `/restaurants/{id}` | Delete a restaurant |
| GET | `/restaurants/search?q={query}` | Search restaurants |

### Reviews
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/restaurants/{restaurantId}/reviews/` | Add a review |
| GET | `/restaurants/{restaurantId}/reviews` | List reviews for a restaurant |
| PUT | `/restaurants/{restaurantId}/reviews/{reviewId}` | Update a review |
| DELETE | `/restaurants/{restaurantId}/reviews/{reviewId}` | Delete a review |
| PUT | `/restaurants/{restaurantId}/reviews/{reviewId}/hide` | Hide/unhide a review (Admin) |

### Users
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET | `/auth/users` | List users (JWT required) |
| PUT | `/auth/users/{id}` | Update a user |
| DELETE | `/auth/users/{id}` | Delete a user |

## TODOs (Improvements)
- [ ] **Separate the `Movie` entity from `MovieDTO`** to improve architecture and avoid coupling.
- [ ] **Ensure controllers use services and DTOs instead of entities**:
  - Controllers should call services instead of directly interacting with repositories.
  - Services should handle business logic and use repositories.
  - DTOs should be used for data transfer instead of exposing entities.
- [ ] **Improve exception handling** by implementing proper error responses.
- [ ] **Enhance security** by refining JWT handling and permissions.
- [ ] **Add integration tests** to verify API functionality.
- [ ] **Optimize database queries** to improve performance.

## Front End Repo
![Frontend Repository](https://github.com/en0l1c/restaurantRatingsFrontend)
