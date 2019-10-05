package xin.lz1998.wcads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xin.lz1998.wcads.entity.WcaRankSingle;

import java.util.List;

public interface WcaRankSingleRepository extends JpaRepository<WcaRankSingle, Integer> {
    public List<WcaRankSingle> findWcaRankSinglesByPersonId(String personId);
    public WcaRankSingle findWcaRankSingleByPersonIdAndEventId(String personId,String eventId);
}
