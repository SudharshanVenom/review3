package restapi.example.demo.entities;

import jakarta.persistence.*;

@Entity
public class WasteBin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String location;
    private int fillLevel;
    private boolean malfunctioning;

    private Long userId; // Storing the userId as a simple Long instead of a JPA relationship

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public int getFillLevel() {
        return fillLevel;
    }

    public boolean isMalfunctioning() {
        return malfunctioning;
    }

    public Long getUserId() {
        return userId; // Getter for userId
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFillLevel(int fillLevel) {
        this.fillLevel = fillLevel;
    }

    public void setMalfunctioning(boolean malfunctioning) {
        this.malfunctioning = malfunctioning;
    }

    public void setUserId(Long userId) {
        this.userId = userId; // Setter for userId
    }
}
