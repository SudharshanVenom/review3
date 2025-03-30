package restapi.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import restapi.example.demo.entities.Route;
import restapi.example.demo.entities.User;
import restapi.example.demo.repositories.RouteRepository;
import restapi.example.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Create a new route
    public Route createRoute(Route route) {
        if (route.getUser() != null && route.getUser().getId() != null) {
            // Fetch the User entity from the database to attach it
            User user = userRepository.findById(route.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + route.getUser().getId()));
            route.setUser(user); // Attach the managed User entity
        }
        return routeRepository.save(route);
    }

    // ✅ Get a route by ID
    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }

    // ✅ Search by name
    public List<Route> searchRoutesByName(String keyword) {
        return routeRepository.searchByRouteName(keyword);
    }

    // ✅ Update a route
    public Route updateRoute(Long id, Route updatedRoute) {
        return routeRepository.findById(id).map(existingRoute -> {
            existingRoute.setRouteName(updatedRoute.getRouteName());
            existingRoute.setPath(updatedRoute.getPath());
    
            if (updatedRoute.getUser() != null && updatedRoute.getUser().getId() != null) {
                // Fetch the User entity from the database before assigning it
                User user = userRepository.findById(updatedRoute.getUser().getId())
                        .orElseThrow(() -> new RuntimeException("User not found with ID: " + updatedRoute.getUser().getId()));
                existingRoute.setUser(user); // Attach the managed User entity
            }
    
            return routeRepository.save(existingRoute);
        }).orElseThrow(() -> new RuntimeException("Route not found with ID: " + id));
    }

    // ✅ Delete a route
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }

    // ✅ Pagination
    public Page<Route> getRoutesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return routeRepository.findAll(pageable);
    }

    // ✅ Pagination with Sorting
    public Page<Route> getRoutesPaginatedSorted(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return routeRepository.findAll(pageable);
    }

    // ✅ Sorting
    public List<Route> getSortedRoutes(String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return routeRepository.findAll(sort);
    }

    // ✅ Get routes by User ID using relationship
    public List<Route> getRoutesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return routeRepository.findByUser(user);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
}
