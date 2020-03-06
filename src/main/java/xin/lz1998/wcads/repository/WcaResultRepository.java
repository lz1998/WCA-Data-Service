package xin.lz1998.wcads.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import xin.lz1998.wcads.entity.WcaResult;
import xin.lz1998.wcads.entity.WcaResultKey;

import java.util.List;

public interface WcaResultRepository extends JpaRepository<WcaResult, WcaResultKey> {
    Page<WcaResult> findWcaResultsByPersonIdAndEventId(String personId, String eventId, Pageable pageable);
    List<WcaResult> findWcaResultsByPersonIdAndEventId(String personId, String eventId);
    Page<WcaResult> findWcaResultsByPersonId(String personId, Pageable pageable);
}
