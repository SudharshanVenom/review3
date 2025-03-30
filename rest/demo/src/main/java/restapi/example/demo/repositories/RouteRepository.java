package restapi.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restapi.example.demo.entities.Route;
import restapi.example.demo.entities.User;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    // ✅ Pagination
    Page<Route> findAll(Pageable pageable);

    // ✅ Sorting
    List<Route> findAll(Sort sort);

    // ✅ Search by routeName (case insensitive)
    @Query("SELECT r FROM Route r WHERE LOWER(r.routeName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Route> searchByRouteName(@Param("keyword") String keyword);

    // ✅ Find routes by User entity (not just ID anymore)
    List<Route> findByUser(User user);
}
