package xin.lz1998.wcads.repository;

import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;
import xin.lz1998.wcads.domain.ResultType;

import java.util.List;

public interface Top10RankRepository {
    List<Top10ResultDTO.Top10ItemDTO> findTop10Rank(Event event, String region, ResultType type, Gender gender);
}
