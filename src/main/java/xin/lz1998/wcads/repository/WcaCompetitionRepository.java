package xin.lz1998.wcads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xin.lz1998.wcads.entity.WcaCompetition;

import java.util.List;

public interface WcaCompetitionRepository extends JpaRepository<WcaCompetition, String> {
    WcaCompetition findWcaCompetitionById(String id);
    List<WcaCompetition> findWcaCompetitionsByIdIn(List<String> competitionIdList);
}
