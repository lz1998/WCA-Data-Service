package xin.lz1998.wcads.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import xin.lz1998.wcads.entity.WcaRankAverage;


public interface WcaRankAverageRepository extends JpaRepository<WcaRankAverage, Integer> {
    Page<WcaRankAverage> findWcaRankAveragesByPersonId(String personId, Pageable pageable);

    WcaRankAverage findWcaRankAverageByPersonIdAndEventId(String personId, String eventId);
}
