package xin.lz1998.wcads.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import xin.lz1998.wcads.entity.WcaRankAverage;
import xin.lz1998.wcads.entity.WcaRankAverageKey;


public interface WcaRankAverageRepository extends JpaRepository<WcaRankAverage, WcaRankAverageKey> {
    Page<WcaRankAverage> findWcaRankAveragesByPersonId(String personId, Pageable pageable);

    WcaRankAverage findWcaRankAverageByPersonIdAndEventId(String personId, String eventId);
}
