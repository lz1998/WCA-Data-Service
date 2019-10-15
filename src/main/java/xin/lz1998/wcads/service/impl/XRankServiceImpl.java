package xin.lz1998.wcads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.lz1998.wcads.entity.WcaRankAverage;
import xin.lz1998.wcads.entity.WcaRankSingle;
import xin.lz1998.wcads.service.WcaRankAverageService;
import xin.lz1998.wcads.service.WcaRankSingleService;
import xin.lz1998.wcads.service.XRankService;
import xin.lz1998.wcads.utils.PageUtil;

import java.util.*;
@Service
public class XRankServiceImpl implements XRankService {
    // TODO 还没检查
    @Autowired
    WcaRankSingleService wcaRankSingleService;
    @Autowired
    WcaRankAverageService wcaRankAverageService;
    @Override
    public Map<String, Object> getRank(List<String> wcaIds) {
        Map<String,List<WcaRankSingle>> singleMap=new HashMap<>();
        Map<String,List<WcaRankAverage>> averageMap=new HashMap<>();

        for(String wcaId : wcaIds){
            List<WcaRankSingle> bestSingleResults = wcaRankSingleService.findBestResultsByPersonId(wcaId, PageUtil.getPageable(1,1000)).getContent();
            for(WcaRankSingle bestResult: bestSingleResults){
                String eventId=bestResult.getEventId();
                if(!singleMap.containsKey(eventId)){
                    singleMap.put(eventId, new ArrayList<>());
                }
                List<WcaRankSingle> wcaRankSingles = singleMap.get(eventId);
                wcaRankSingles.add(bestResult);
            }

            List<WcaRankAverage> bestAverageResults = wcaRankAverageService.findBestResultsByPersonId(wcaId,PageUtil.getPageable(1,1000)).getContent();
            for(WcaRankAverage bestResult: bestAverageResults){
                String eventId=bestResult.getEventId();
                if(!averageMap.containsKey(eventId)){
                    averageMap.put(eventId, new ArrayList<>());
                }
                List<WcaRankAverage> wcaRankAverages = averageMap.get(eventId);
                wcaRankAverages.add(bestResult);
            }
        }
        for(Map.Entry<String,List<WcaRankSingle>> entry:singleMap.entrySet()){
            entry.getValue().sort(Comparator.comparingInt(WcaRankSingle::getBest));
        }
        for(Map.Entry<String,List<WcaRankAverage>> entry:averageMap.entrySet()){
            entry.getValue().sort(Comparator.comparingInt(WcaRankAverage::getBest));
        }

        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("single",singleMap);
        resultMap.put("average",averageMap);
        return resultMap;
    }
}
