package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.service.WcaResultService;
import xin.lz1998.wcads.utils.PageUtil;
import xin.lz1998.wcads.utils.ResultWrapperUtils;

@CrossOrigin("*")
@RequestMapping("/wcaResult")
@RestController
public class WcaResultController {
    @Autowired
    WcaResultService wcaResultService;


    @RequestMapping("/findResultsByPersonIdAndEventId")
    public Object findResultsByPersonIdAndEventId(String personId, String eventId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageUtil.getPageable(pageNum, pageSize);
        Page data = wcaResultService.findResultsByPersonIdAndEventId(personId, eventId, pageable);
        return ResultWrapperUtils.pageResultWrapper(data);
    }

    @RequestMapping("/findResultsByPersonId")
    public Object findResultsByPersonId(String personId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageUtil.getPageable(pageNum, pageSize);
        Page data = wcaResultService.findResultsByPersonId(personId, pageable);
        return ResultWrapperUtils.pageResultWrapper(data);
    }
    @RequestMapping("/findResultsByPersonIdAndEventIdOrderByDateAndRound")
    public Object findResultsByPersonIdAndEventIdOrderByDateAndRound(String personId,String eventId){
        return ResultWrapperUtils.resultWrapper(wcaResultService.findResultsByPersonIdAndEventIdOrderByDateAndRound(personId,eventId));
    }
}
