package restapi.example.demo.repositories;

import org.springframework.data.domain.Sort;
import restapi.example.demo.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find users by role
    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findByRole(String role);

    // Sorting
    List<User> findAll(Sort sort);

    // Search by email
    @Query("SELECT u FROM User u WHERE LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<User> searchByEmail(String keyword);

    // Pagination & Sorting
    Page<User> findAll(Pageable pageable);

    // Exact match by email
    List<User> findByEmail(String email);

    // ✅ Optional: Fetch user with routes eagerly
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.routes WHERE u.id = :userId")
    User findUserWithRoutes(Long userId);
}
