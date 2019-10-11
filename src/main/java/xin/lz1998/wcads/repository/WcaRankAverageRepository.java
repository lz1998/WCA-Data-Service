package xin.lz1998.wcads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xin.lz1998.wcads.entity.WcaRankAverage;

import java.util.List;

public interface WcaRankAverageRepository extends JpaRepository<WcaRankAverage,Integer> {
    List<WcaRankAverage> findWcaRankAveragesByPersonId(String personId);
    WcaRankAverage findWcaRankAverageByPersonIdAndEventId(String personId, String eventId);
}
