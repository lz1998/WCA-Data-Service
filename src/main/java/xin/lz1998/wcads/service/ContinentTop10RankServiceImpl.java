package xin.lz1998.wcads.service;

import org.springframework.stereotype.Service;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;
import xin.lz1998.wcads.domain.Region;
import xin.lz1998.wcads.domain.ResultType;
import xin.lz1998.wcads.repository.Top10RankRepository;

import java.util.List;

@Service
public class ContinentTop10RankServiceImpl implements Top10RankService {
    private Top10RankRepository top10RankRepository;

    public ContinentTop10RankServiceImpl(Top10RankRepository top10RankRepository) {
        this.top10RankRepository = top10RankRepository;
    }

    @Override
    public List<Top10ResultDTO.Top10ItemDTO> searchTop10Rank(Event event, String region, ResultType type, Gender gender) {
        return top10RankRepository.findTop10RankForContinent(event, region, type, gender);
    }

    @Override
    public boolean matchRegionType(String region) {
        return Region.isContinentRecord(region);
    }

    @Override
    public String getRealRegionName(String region) {
        return region;
    }

}
