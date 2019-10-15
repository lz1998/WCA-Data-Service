package xin.lz1998.wcads.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import xin.lz1998.wcads.entity.WcaResult;

public interface WcaResultRepository extends JpaRepository<WcaResult, Integer> {
    Page<WcaResult> findWcaResultsByPersonIdAndEventId(String personId, String eventId, Pageable pageable);

    Page<WcaResult> findWcaResultsByPersonId(String personId, Pageable pageable);
}
