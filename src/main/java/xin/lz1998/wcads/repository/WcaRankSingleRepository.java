package xin.lz1998.wcads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xin.lz1998.wcads.entity.WcaRankSingle;

import java.util.List;

public interface WcaRankSingleRepository extends JpaRepository<WcaRankSingle, Integer> {
    List<WcaRankSingle> findWcaRankSinglesByPersonId(String personId);
    WcaRankSingle findWcaRankSingleByPersonIdAndEventId(String personId, String eventId);
}
