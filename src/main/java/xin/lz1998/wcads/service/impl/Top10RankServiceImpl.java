package xin.lz1998.wcads.service.impl;

import com.google.common.collect.Lists;
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
import static xin.lz1998.wcads.domain.Region.WORLD_RECORD;

@Service
public class Top10RankServiceImpl implements Top10RankService {

    public static final String CHINA = "China";

    private Top10RankRepository top10RankRepository;

    public Top10RankServiceImpl(Top10RankRepository top10RankRepository) {
        this.top10RankRepository = top10RankRepository;
    }

    @Override
    public Top10ResultDTO searchTop10Rank(Event event, String region, ResultType type, Gender gender) {
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = Lists.newArrayList();
        if (region.equals(WORLD_RECORD.getBriefName())) {
            if (type.equals(ResultType.SINGLE)) {
                top10Rank = top10RankRepository.findTop10RankSingleResultForWholeWorld(event, gender);
            } else if (type.equals(ResultType.AVERAGE)) {
                top10Rank = top10RankRepository.findTop10RankAverageResultForWholeWorld(event, gender);
            }
        } else if (Region.isContinentRecord(region)) {
            if (type.equals(ResultType.SINGLE)) {
                top10Rank = top10RankRepository.findTop10RankSingleResultForContinent(event, Region.from(region), gender);
            } else if (type.equals(ResultType.AVERAGE)) {
                top10Rank = top10RankRepository.findTop10RankAverageResultForContinent(event, Region.from(region), gender);
            }
        } else if (region.equals(NATION_RECORD.getBriefName())) {
            top10Rank = getTop10RankForCountry(event, CHINA, type, gender);
        } else {
            top10Rank = getTop10RankForCountry(event, region, type, gender);
        }
        return Top10ResultDTO.builder().top10ItemDTOList(top10Rank).build();
    }

    private List<Top10ResultDTO.Top10ItemDTO> getTop10RankForCountry(Event event, String country, ResultType type, Gender gender) {
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = Lists.newArrayList();
        if (type.equals(ResultType.SINGLE)) {
            top10Rank = top10RankRepository.findTop10RankForCountryAndSingleResult(event, country, gender);
        } else if (type.equals(ResultType.AVERAGE)) {
            top10Rank = top10RankRepository.findTop10RankForCountryAndAverageResult(event, country, gender);
        }
        return top10Rank;
    }
}
