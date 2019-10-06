package xin.lz1998.wcads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xin.lz1998.wcads.entity.WcaResult;

import java.util.List;

public interface WcaResultRepository extends JpaRepository<WcaResult,Integer> {
    List<WcaResult> findWcaResultsByPersonIdAndEventId(String personId,String eventId);
}
