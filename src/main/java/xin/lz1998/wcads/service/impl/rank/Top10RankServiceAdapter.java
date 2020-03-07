package xin.lz1998.wcads.service.impl.rank;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;
import xin.lz1998.wcads.domain.ResultType;
import xin.lz1998.wcads.service.Top10RankService;

import java.util.List;

@Service
public class Top10RankServiceAdapter {

    private List<Top10RankService> top10RankServices;

    public Top10RankServiceAdapter(List<Top10RankService> top10RankServices) {
        this.top10RankServices = top10RankServices;
    }

    public Top10ResultDTO getTop10Rank(Event event, String region, ResultType type, Gender gender) {
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankServices.stream()
                .filter(service -> service.matchRegionType(region))
                .findFirst()
                .map(service -> service.searchTop10Rank(event, service.getRealRegionName(region), type, gender))
                .orElse(Lists.newArrayList());
        return Top10ResultDTO.builder().top10ItemDTOList(top10Rank).build();
    }

}
