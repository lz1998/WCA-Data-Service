package xin.lz1998.wcads.repository;

import xin.lz1998.wcads.controller.dto.Top10ResultDTO;

import java.util.List;

public interface Top10RankRepository {
    List<Top10ResultDTO.Top10ItemDTO> findTop10Rank(String event, String region, String type, String gender);
}
