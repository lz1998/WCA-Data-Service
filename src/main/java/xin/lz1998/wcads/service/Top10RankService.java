package xin.lz1998.wcads.service;

import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;
import xin.lz1998.wcads.domain.ResultType;

import java.util.List;

public interface Top10RankService {
    List<Top10ResultDTO.Top10ItemDTO> searchTop10Rank(Event event, String region, ResultType type, Gender gender);
    boolean matchRegionType(String region);
    String getRealRegionName(String region);
}
