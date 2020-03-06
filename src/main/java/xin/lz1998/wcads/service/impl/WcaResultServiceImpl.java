package xin.lz1998.wcads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xin.lz1998.wcads.entity.WcaCompetition;
import xin.lz1998.wcads.entity.WcaResult;
import xin.lz1998.wcads.repository.WcaCompetitionRepository;
import xin.lz1998.wcads.repository.WcaResultRepository;
import xin.lz1998.wcads.service.WcaResultService;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class WcaResultServiceImpl implements WcaResultService {
    @Autowired
    WcaResultRepository wcaResultRepository;

    @Autowired
    WcaCompetitionRepository wcaCompetitionRepository;

    private static List<String> roundTypeOrder=new ArrayList<>();

    static {
        roundTypeOrder.add("h");
        roundTypeOrder.add("0");
        roundTypeOrder.add("d");
        roundTypeOrder.add("1");
        roundTypeOrder.add("b");
        roundTypeOrder.add("2");
        roundTypeOrder.add("e");
        roundTypeOrder.add("g");
        roundTypeOrder.add("3");
        roundTypeOrder.add("c");
        roundTypeOrder.add("f");
    }

    public Page findResultsByPersonIdAndEventId(String personId, String eventId, Pageable pageable) {
        return wcaResultRepository.findWcaResultsByPersonIdAndEventId(personId, eventId, pageable);
    }

    @Override
    public Page findResultsByPersonId(String personId, Pageable pageable) {
        return wcaResultRepository.findWcaResultsByPersonId(personId,pageable);
    }

    @Override
    public Object findResultsByPersonIdAndEventIdOrderByDateAndRound(String personId, String eventId) {
        List<WcaResult> wcaResultList = wcaResultRepository.findWcaResultsByPersonIdAndEventId(personId, eventId);
        List<String> competitionIdList=wcaResultList.stream().map(WcaResult::getCompetitionId).collect(Collectors.toList());
        List<WcaCompetition> wcaCompetitionList = wcaCompetitionRepository.findWcaCompetitionsByIdIn(competitionIdList);
        Map<String,WcaCompetition> wcaCompetitionMap=new HashMap<>();
        wcaCompetitionList.forEach(wcaCompetition -> wcaCompetitionMap.put(wcaCompetition.getId(),wcaCompetition));
        wcaResultList.sort((o1, o2) -> {
            WcaCompetition competition1 = wcaCompetitionMap.get(o1.getCompetitionId());
            WcaCompetition competition2 = wcaCompetitionMap.get(o2.getCompetitionId());
            // YEAR防止溢出，-1900
            // MONTH不做处理
            // DAY范围20~620，给roundType留11个位置
            // roundType范围0-10
            Integer v1=(competition1.getYear()-1900)*10000000+competition1.getMonth()*1000+competition1.getDay()*20+roundTypeOrder.indexOf(o1.getRoundTypeId());
            Integer v2=(competition2.getYear()-1900)*10000000+competition2.getMonth()*1000+competition2.getDay()*20+roundTypeOrder.indexOf(o2.getRoundTypeId());
            return (v1-v2);
        });

        return wcaResultList;
    }


}
