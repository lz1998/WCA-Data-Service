package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.entity.WcaRankAverage;
import xin.lz1998.wcads.service.WcaRankAverageService;
import xin.lz1998.wcads.utils.PageUtil;
import xin.lz1998.wcads.utils.ResultWrapperUtils;

@CrossOrigin("*")
@RequestMapping("/wcaAverage")
@RestController
public class WcaAverageController {
    @Autowired
    private WcaRankAverageService wcaRankAverageService;

    @RequestMapping("/findBestResultsByPersonId")
    public Object findBestResultsByPersonId(String personId,Integer pageNum,Integer pageSize) {
        Pageable pageable= PageUtil.getPageable(pageNum,pageSize);
        Page data = wcaRankAverageService.findBestResultsByPersonId(personId, pageable);
        return ResultWrapperUtils.pageResultWrapper(data);
    }
    @RequestMapping("/findBestResultByPersonIdAndEventId")
    public Object findBestResultByPersonIdAndEventId(String personId, String eventId) {
        WcaRankAverage data=wcaRankAverageService.findBestResultByPersonIdAndEventId(personId,eventId);
        return ResultWrapperUtils.resultWrapper(data);
    }
}
