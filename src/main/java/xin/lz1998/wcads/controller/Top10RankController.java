package xin.lz1998.wcads.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;
import xin.lz1998.wcads.domain.ResultType;
import xin.lz1998.wcads.service.Top10RankService;

@RestController
@RequestMapping("/top10rank")
public class Top10RankController {
    private Top10RankService top10RankService;

    public Top10RankController(Top10RankService top10RankService) {
        this.top10RankService = top10RankService;
    }

    @GetMapping()
    public Top10ResultDTO getTop10Rank(@RequestParam(value = "event") Event event,
                                       @RequestParam(value = "region", required = false, defaultValue = "nr") String region,
                                       @RequestParam(value = "type", required = false, defaultValue = "sin") ResultType type,
                                       @RequestParam(value = "gender", required = false, defaultValue = "all") Gender gender) {
        return top10RankService.searchTop10Rank(event, region, type, gender);
    }

}
