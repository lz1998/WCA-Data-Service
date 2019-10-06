package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.entity.WcaRankAverage;
import xin.lz1998.wcads.service.WcaRankAverageService;

import java.util.List;
@CrossOrigin("*")
@RequestMapping("/wcaAverage")
@RestController
public class WcaAverageController {
    @Autowired
    private WcaRankAverageService wcaRankAverageService;
    @RequestMapping("/importData")
    public Object importData() {
        wcaRankAverageService.importData();
        return "ok";
    }
    @RequestMapping("/findBestResultsByPersonId")
    public List<WcaRankAverage> findBestResultsByPersonId(String personId) {
        return wcaRankAverageService.findBestResultsByPersonId(personId);
    }
    @RequestMapping("/findBestResultByPersonIdAndEventId")
    public WcaRankAverage findBestResultByPersonIdAndEventId(String personId, String eventId) {
        return wcaRankAverageService.findBestResultByPersonIdAndEventId(personId,eventId);
    }
}
