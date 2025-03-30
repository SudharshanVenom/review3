package restapi.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.example.demo.entities.Route;
import restapi.example.demo.Service.RouteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "*")
public class RouteController {

    @Autowired
    private RouteService routeService;

    // ✅ Create a new route
    @PostMapping("/add")
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        Route savedRoute = routeService.createRoute(route);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoute);
    }

    // ✅ Get all routes
    @GetMapping("/all")
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    // ✅ Get route by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
        Optional<Route> route = routeService.getRouteById(id);
        return route.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Update a route
    @PutMapping("/update/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable Long id, @RequestBody Route route) {
        Route updatedRoute = routeService.updateRoute(id, route);
        if (updatedRoute != null) {
            return ResponseEntity.ok(updatedRoute);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Delete a route
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.ok("Route  deleted successfully");
    }

    // ✅ Pagination
    @GetMapping("/paginate")
    public Page<Route> getRoutesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return routeService.getRoutesPaginated(page, size);
    }

    // ✅ Pagination with Sorting
    @GetMapping("/paginate-sorted")
    public Page<Route> getRoutesPaginatedSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        return routeService.getRoutesPaginatedSorted(page, size, sortBy, sortDirection);
    }

    // ✅ Sorting
    @GetMapping("/sort")
    public List<Route> getSortedRoutes(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        return routeService.getSortedRoutes(sortBy, sortDirection);
    }

    // ✅ JPQL Query: Get routes by userId
    @GetMapping("/user/{userId}")
    public List<Route> getRoutesByUserId(@PathVariable Long userId) {
        return routeService.getRoutesByUserId(userId);
    }

    // ✅ Search by route name (case-insensitive)
    @GetMapping("/search")
    public List<Route> searchRoutes(@RequestParam String keyword) {
        return routeService.searchRoutesByName(keyword);
    }
}
