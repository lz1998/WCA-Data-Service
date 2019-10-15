package xin.lz1998.wcads.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import xin.lz1998.wcads.entity.WcaRankSingle;


public interface WcaRankSingleRepository extends JpaRepository<WcaRankSingle, Integer> {
    Page<WcaRankSingle> findWcaRankSinglesByPersonId(String personId, Pageable pageable);

    WcaRankSingle findWcaRankSingleByPersonIdAndEventId(String personId, String eventId);
}
