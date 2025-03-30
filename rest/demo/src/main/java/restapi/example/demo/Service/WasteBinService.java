package restapi.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import restapi.example.demo.entities.WasteBin;
import restapi.example.demo.repositories.WasteBinRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WasteBinService {

    @Autowired
    private WasteBinRepository wasteBinRepository;

    // ✅ Save WasteBin (Create)
    public WasteBin saveWasteBin(WasteBin wasteBin) {
        return wasteBinRepository.save(wasteBin);
    }

    // ✅ Get All WasteBins
    public List<WasteBin> getAllWasteBins() {
        return wasteBinRepository.findAll();
    }

    // ✅ Get WasteBin by ID
    public Optional<WasteBin> getWasteBinById(Long id) {
        return wasteBinRepository.findById(id);
    }

    // ✅ Update WasteBin
    public WasteBin updateWasteBin(Long id, WasteBin wasteBinDetails) {
        WasteBin wasteBin = wasteBinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WasteBin not found"));

        wasteBin.setLocation(wasteBinDetails.getLocation());
        wasteBin.setFillLevel(wasteBinDetails.getFillLevel());
        wasteBin.setMalfunctioning(wasteBinDetails.isMalfunctioning());
        wasteBin.setUserId(wasteBinDetails.getUserId());

        return wasteBinRepository.save(wasteBin);
    }

    // ✅ Delete WasteBin
    public void deleteWasteBin(Long id) {
        WasteBin wasteBin = wasteBinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WasteBin not found"));
        wasteBinRepository.delete(wasteBin);
    }

    // ✅ Pagination and Sorting
    public Page<WasteBin> getPaginatedWasteBins(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return wasteBinRepository.findAll(pageable);
    }

    // ✅ Find bins by location
    public List<WasteBin> findWasteBinsByLocation(String location) {
        return wasteBinRepository.findByLocation(location);
    }

    // ✅ Find malfunctioning bins
    public List<WasteBin> findMalfunctioningBins() {
        return wasteBinRepository.findMalfunctioningBins();
    }

    // ✅ Find bins by userId
    public List<WasteBin> findWasteBinsByUserId(Long userId) {
        return wasteBinRepository.findByUserId(userId);
    }
}
