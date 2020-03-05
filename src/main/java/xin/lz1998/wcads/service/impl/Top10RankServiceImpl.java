package xin.lz1998.wcads.service.impl;

import org.springframework.stereotype.Service;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.repository.Top10RankRepository;
import xin.lz1998.wcads.service.Top10RankService;

import java.util.List;

@Service
public class Top10RankServiceImpl implements Top10RankService {
    private Top10RankRepository top10RankRepository;

    public Top10RankServiceImpl(Top10RankRepository top10RankRepository) {
        this.top10RankRepository = top10RankRepository;
    }

    @Override
    public Top10ResultDTO searchTop10Rank(String event, String region, String type, String gender) {
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10Rank(event, region, type, gender);
        return Top10ResultDTO.builder().top10ItemDTOList(top10Rank).build();
    }
}
