package xin.lz1998.wcads.service;

import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;
import xin.lz1998.wcads.domain.ResultType;

public interface Top10RankService {
    Top10ResultDTO searchTop10Rank(Event event, String region, ResultType type, Gender gender);
}
