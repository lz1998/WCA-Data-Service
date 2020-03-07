package xin.lz1998.wcads.service.impl.rank;

import org.springframework.stereotype.Service;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;
import xin.lz1998.wcads.domain.Region;
import xin.lz1998.wcads.domain.ResultType;
import xin.lz1998.wcads.repository.Top10RankRepository;
import xin.lz1998.wcads.service.Top10RankService;

import java.util.List;

import static xin.lz1998.wcads.domain.Region.NATION_RECORD;

@Service
public class CountryTop10RankServiceImpl implements Top10RankService {
    public static final String CHINA = "China";

    private Top10RankRepository top10RankRepository;

    public CountryTop10RankServiceImpl(Top10RankRepository top10RankRepository) {
        this.top10RankRepository = top10RankRepository;
    }

    @Override
    public List<Top10ResultDTO.Top10ItemDTO> searchTop10Rank(Event event, String country, ResultType type, Gender gender) {
        return top10RankRepository.findTop10RankForCountry(event, country, type, gender);
    }

    @Override
    public boolean matchRegionType(String region) {
        return Region.isCountryRecord(region);
    }

    @Override
    public String getRealRegionName(String region) {
        if (region.equalsIgnoreCase(NATION_RECORD.getBriefName())) {
            return CHINA;
        }
        return region;
    }
}
