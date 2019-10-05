package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.entity.WcaRankSingle;
import xin.lz1998.wcads.service.WcaRankSingleService;

import java.util.List;

@RequestMapping("/wcaSingle")
@RestController
public class WcaSingleController {
    @Autowired
    private WcaRankSingleService wcaRankSingleService;
    @RequestMapping("/importData")
    public Object importData(){
        wcaRankSingleService.importData();
        return "ok";
    }
    @RequestMapping("/findBestResultsByPersonId")
    public List<WcaRankSingle> findBestResultsByPersonId(String personId) {
        return wcaRankSingleService.findBestResultsByPersonId(personId);
    }
    @RequestMapping("/findBestResultByPersonIdAndEventId")
    public WcaRankSingle findBestResultByPersonIdAndEventId(String personId, String eventId) {
        return wcaRankSingleService.findBestResultsByPersonIdAndEventId(personId,eventId);
    }
}
