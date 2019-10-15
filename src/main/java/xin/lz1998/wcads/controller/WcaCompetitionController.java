package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.entity.WcaCompetition;
import xin.lz1998.wcads.service.WcaCompetitionService;
import xin.lz1998.wcads.utils.ResultWrapperUtils;

@CrossOrigin("*")
@RequestMapping("/wcaCompetition")
@RestController
public class WcaCompetitionController {
    @Autowired
    WcaCompetitionService wcaCompetitionService;

    @RequestMapping("/findCompetitionById")
    public Object findWcaCompetitionById(String id){
        WcaCompetition data=wcaCompetitionService.findWcaCompetitionById(id);
        return ResultWrapperUtils.resultWrapper(data);
    }
}
