package xin.lz1998.wcads.service.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;
import xin.lz1998.wcads.domain.ResultType;
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
    public Top10ResultDTO searchTop10Rank(Event event, String region, ResultType type, Gender gender) {
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = Lists.newArrayList();
        if (type.equals(ResultType.SINGLE)) {
            top10Rank = top10RankRepository.findTop10RankForCountryAndSingleResult(event, region, gender);
        } else if (type.equals(ResultType.AVERAGE)) {
            top10Rank = top10RankRepository.findTop10RankForCountryAndAverageResult(event, region, gender);
        }
        return Top10ResultDTO.builder().top10ItemDTOList(top10Rank).build();
    }
}
