package xin.lz1998.wcads.repository;

import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;
import xin.lz1998.wcads.domain.Region;

import java.util.List;

public interface Top10RankRepository {
    List<Top10ResultDTO.Top10ItemDTO> findTop10RankForCountryAndSingleResult(Event event, String region, Gender gender);

    List<Top10ResultDTO.Top10ItemDTO> findTop10RankForCountryAndAverageResult(Event event, String region, Gender gender);

    List<Top10ResultDTO.Top10ItemDTO> findTop10RankSingleResultForWholeWorld(Event event, Gender gender);

    List<Top10ResultDTO.Top10ItemDTO> findTop10RankAverageResultForWholeWorld(Event event, Gender gender);

    List<Top10ResultDTO.Top10ItemDTO> findTop10RankSingleResultForContinent(Event event, Region region, Gender gender);

    List<Top10ResultDTO.Top10ItemDTO> findTop10RankAverageResultForContinent(Event event, Region region, Gender gender);
}
