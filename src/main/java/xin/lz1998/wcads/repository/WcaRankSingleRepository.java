package xin.lz1998.wcads.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import xin.lz1998.wcads.entity.WcaRankSingle;
import xin.lz1998.wcads.entity.WcaRankSingleKey;


public interface WcaRankSingleRepository extends JpaRepository<WcaRankSingle, WcaRankSingleKey> {
    Page<WcaRankSingle> findWcaRankSinglesByPersonId(String personId, Pageable pageable);

    WcaRankSingle findWcaRankSingleByPersonIdAndEventId(String personId, String eventId);
}
