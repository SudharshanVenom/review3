package restapi.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String routeName;

    @Column(nullable = false)
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // creates a user_id column in the routes table
    @JsonBackReference
    private User user;

    // --- Constructors ---
    public Route() {}

    public Route(String routeName, String path, User user) {
        this.routeName = routeName;
        this.path = path;
        this.user = user;
    }

    // --- Getters & Setters ---
    public Long getId() {
        return id;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getPath() {
        return path;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
