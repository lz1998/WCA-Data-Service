package xin.lz1998.wcads.service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;
import xin.lz1998.wcads.domain.Region;
import xin.lz1998.wcads.domain.ResultType;
import xin.lz1998.wcads.repository.Top10RankRepository;

import java.util.List;

@Service
public class WorldTop10RankServiceImpl implements Top10RankService {
    private Top10RankRepository top10RankRepository;

    public WorldTop10RankServiceImpl(Top10RankRepository top10RankRepository) {
        this.top10RankRepository = top10RankRepository;
    }

    @Override
    public List<Top10ResultDTO.Top10ItemDTO> searchTop10Rank(Event event, String region, ResultType type, Gender gender) {
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = Lists.newArrayList();
        if (type.equals(ResultType.SINGLE)) {
            top10Rank = top10RankRepository.findTop10RankSingleResultForWholeWorld(event, gender);
        } else if (type.equals(ResultType.AVERAGE)) {
            top10Rank = top10RankRepository.findTop10RankAverageResultForWholeWorld(event, gender);
        }
        return top10Rank;
    }

    @Override
    public boolean matchRegionType(String region) {
        return Region.isWorldRecord(region);
    }

    @Override
    public String getRealRegionName(String region) {
        return null;
    }
}
