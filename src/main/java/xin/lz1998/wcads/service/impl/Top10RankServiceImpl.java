package xin.lz1998.wcads.service.impl;

import org.springframework.stereotype.Service;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.service.Top10RankService;

@Service
public class Top10RankServiceImpl implements Top10RankService {
    @Override
    public Top10ResultDTO searchTop10Rank(String event, String region, String type, String gender) {
        return Top10ResultDTO.builder().build();
    }
}
