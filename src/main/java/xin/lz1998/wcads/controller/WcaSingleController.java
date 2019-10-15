package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.entity.WcaRankSingle;
import xin.lz1998.wcads.service.WcaRankSingleService;
import xin.lz1998.wcads.utils.PageUtil;
import xin.lz1998.wcads.utils.ResultWrapperUtils;

import java.util.List;
@CrossOrigin("*")
@RequestMapping("/wcaSingle")
@RestController
public class WcaSingleController {
    @Autowired
    private WcaRankSingleService wcaRankSingleService;

    @RequestMapping("/findBestResultsByPersonId")
    public Object findBestResultsByPersonId(String personId,Integer pageNum,Integer pageSize) {
        Pageable pageable= PageUtil.getPageable(pageNum,pageSize);
        Page data = wcaRankSingleService.findBestResultsByPersonId(personId, pageable);
        return ResultWrapperUtils.pageResultWrapper(data);
    }
    @RequestMapping("/findBestResultByPersonIdAndEventId")
    public Object findBestResultByPersonIdAndEventId(String personId, String eventId) {
        WcaRankSingle data = wcaRankSingleService.findBestResultsByPersonIdAndEventId(personId, eventId);
        return ResultWrapperUtils.resultWrapper(data);

    }
}
