package restapi.example.demo.entities;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String role;

    @Version
    private Integer version;

    // ✅ One-to-Many relationship with Route
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Route> routes;

    // --- Getters & Setters ---
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public Integer getVersion() {
        return version;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
