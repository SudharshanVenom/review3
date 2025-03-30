package restapi.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.example.demo.Service.UserService;
import restapi.example.demo.entities.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Create new user
    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // ✅ Get all users
    @GetMapping("/get")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ✅ Get user by ID
    @GetMapping("get/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // ✅ Update user
    @PutMapping("put/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // ✅ Delete user
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User  deleted successfully");
    }

    // ✅ Get users by role
    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(@PathVariable String role) {
        return userService.getUsersByRole(role);
    }

    // ✅ Search users by email
    @GetMapping("/search/email")
    public List<User> searchByEmail(@RequestParam String keyword) {
        return userService.getUsersByEmail(keyword);
    }

    // ✅ Get sorted users
    @GetMapping("/sorted")
    public List<User> getSortedUsers(@RequestParam String sortBy, @RequestParam String order) {
        return userService.getSortedUsers(sortBy, order);
    }

    // ✅ Paginated users
    @GetMapping("/paginated")
    public Page<User> getPaginatedUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        return userService.getAllUsersPaginated(page, size, sortBy, order);
    }

    // ✅ Get user with routes
    @GetMapping("/{id}/routes")
    public User getUserWithRoutes(@PathVariable Long id) {
        return userService.getUserWithRoutes(id);
    }
}
