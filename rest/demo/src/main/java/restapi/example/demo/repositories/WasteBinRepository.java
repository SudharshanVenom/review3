package restapi.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import restapi.example.demo.entities.WasteBin;

import java.util.List;

public interface WasteBinRepository extends JpaRepository<WasteBin, Long> {

    // ✅ Custom JPQL Query: Find bins by location
    @Query("SELECT w FROM WasteBin w WHERE w.location = :location")
    List<WasteBin> findByLocation(String location);

    // ✅ Custom JPQL Query: Find malfunctioning bins
    @Query("SELECT w FROM WasteBin w WHERE w.malfunctioning = true")
    List<WasteBin> findMalfunctioningBins();

    // ✅ Find bins by userId
    @Query("SELECT w FROM WasteBin w WHERE w.userId = :userId")
    List<WasteBin> findByUserId(Long userId);

    // ✅ Pagination and Sorting
    Page<WasteBin> findAll(Pageable pageable);
}
