package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.entity.WcaRankAverage;
import xin.lz1998.wcads.service.WcaRankAverageService;

import java.util.List;

@RequestMapping("/wcaRankAverage")
@RestController
public class WcaRankAverageController {
    @Autowired
    private WcaRankAverageService wcaRankAverageService;
    @RequestMapping("/importData")
    public Object importData() {
        wcaRankAverageService.importData();
        return "ok";
    }
    @RequestMapping("/findWcaRankAveragesByPersonId")
    public List<WcaRankAverage> findWcaRankAveragesByPersonId(String personId) {
        return wcaRankAverageService.findWcaRankAveragesByPersonId(personId);
    }
    @RequestMapping("/findWcaRankAverageByPersonIdAndEventId")
    public WcaRankAverage findWcaRankAverageByPersonIdAndEventId(String personId, String eventId) {
        return wcaRankAverageService.findWcaRankAverageByPersonIdAndEventId(personId,eventId);
    }
}
