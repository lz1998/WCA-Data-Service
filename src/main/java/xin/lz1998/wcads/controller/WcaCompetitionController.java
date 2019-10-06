package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.service.WcaCompetitionService;

@CrossOrigin("*")
@RequestMapping("/wcaCompetition")
@RestController
public class WcaCompetitionController {
    @Autowired
    WcaCompetitionService wcaCompetitionService;

    @RequestMapping("/importData")
    public Object importData(){
        wcaCompetitionService.importData();
        return "ok";
    }
}
