package xin.lz1998.wcads.service;

import xin.lz1998.wcads.controller.dto.Top10ResultDTO;

public interface Top10RankService {
    Top10ResultDTO searchTop10Rank(String event, String region, String type, String gender);
}
