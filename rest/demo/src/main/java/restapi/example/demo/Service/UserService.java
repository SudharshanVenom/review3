package restapi.example.demo.Service;

import restapi.example.demo.entities.User;
import restapi.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ✅ Create or Update User
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // ✅ Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Update a User by ID
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setRole(updatedUser.getRole());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    // ✅ Get All Users with Pagination and Sorting
    public Page<User> getAllUsersPaginated(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageable);
    }

    // ✅ Get User by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // ✅ Delete User
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // ✅ Find Users by Role
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    // ✅ Search Users by Email
    public List<User> getUsersByEmail(String keyword) {
        return userRepository.searchByEmail(keyword);
    }

    // ✅ Sort Users
    public List<User> getSortedUsers(String field, String order) {
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(field).ascending() : Sort.by(field).descending();
        return userRepository.findAll(sort);
    }

    // ✅ Get User with Routes (Using @OneToMany mapping)
    public User getUserWithRoutes(Long userId) {
        return userRepository.findUserWithRoutes(userId);
    }
}
