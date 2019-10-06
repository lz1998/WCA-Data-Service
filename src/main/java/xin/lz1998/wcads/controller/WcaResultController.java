package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.entity.WcaResult;
import xin.lz1998.wcads.service.WcaResultService;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/wcaResult")
@RestController
public class WcaResultController {
    @Autowired
    WcaResultService wcaResultService;


    @RequestMapping("/importData")
    public Object importData(){
        wcaResultService.importData();
        return "ok";
    }

    @RequestMapping("/findResultsByPersonIdAndEventId")
    public List<WcaResult> findResultsByPersonIdAndEventId(String personId, String eventId) {
        return wcaResultService.findResultsByPersonIdAndEventId(personId,eventId);
    }
}
