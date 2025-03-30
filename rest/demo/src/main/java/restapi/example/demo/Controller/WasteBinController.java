package restapi.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.example.demo.entities.WasteBin;
import restapi.example.demo.Service.WasteBinService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wastebins")
public class WasteBinController {

    @Autowired
    private WasteBinService wasteBinService;

    // ✅ Create a new WasteBin
    @PostMapping("/add")
    public WasteBin createWasteBin(@RequestBody WasteBin wasteBin) {
        return wasteBinService.saveWasteBin(wasteBin);
    }

    // ✅ Get all WasteBins
    @GetMapping("/get")
    public List<WasteBin> getAllWasteBins() {
        return wasteBinService.getAllWasteBins();
    }

    // ✅ Get WasteBins with Pagination & Sorting
    @GetMapping("/all")
    public Page<WasteBin> getPaginatedWasteBins(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return wasteBinService.getPaginatedWasteBins(page, size, sortBy, direction);
    }

    // ✅ Get WasteBin by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<WasteBin> getWasteBinById(@PathVariable Long id) {
        Optional<WasteBin> wasteBin = wasteBinService.getWasteBinById(id);
        return wasteBin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Update WasteBin
    @PutMapping("/update/{id}")
    public ResponseEntity<WasteBin> updateWasteBin(@PathVariable Long id, @RequestBody WasteBin wasteBinDetails) {
        try {
            return ResponseEntity.ok(wasteBinService.updateWasteBin(id, wasteBinDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Delete WasteBin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWasteBin(@PathVariable Long id) {
        wasteBinService.deleteWasteBin(id);
        return ResponseEntity.ok("WasteBin deleted successfully");
    }

    // ✅ JPQL Query: Find WasteBins by Location
    @GetMapping("/search/location")
    public List<WasteBin> findWasteBinsByLocation(@RequestParam String location) {
        return wasteBinService.findWasteBinsByLocation(location);
    }

    // ✅ JPQL Query: Find Malfunctioning Bins
    @GetMapping("/search/malfunctioning")
    public List<WasteBin> findMalfunctioningBins() {
        return wasteBinService.findMalfunctioningBins();
    }

    // ✅ Find WasteBins by User ID
    @GetMapping("/search/user/{userId}")
    public List<WasteBin> findWasteBinsByUserId(@PathVariable Long userId) {
        return wasteBinService.findWasteBinsByUserId(userId);
    }
}
