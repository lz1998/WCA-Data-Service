package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.entity.WcaRankSingle;
import xin.lz1998.wcads.service.WcaRankSingleService;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@RequestMapping("/wcaRankSingle")
@RestController
public class WcaRankSingleController {
    @Autowired
    private WcaRankSingleService wcaRankSingleService;
    @RequestMapping("/importData")
    public Object importData(){
        wcaRankSingleService.importData();
        return "ok";
    }
    @RequestMapping("/findWcaRankSinglesByPersonId")
    public List<WcaRankSingle> findWcaRankSinglesByPersonId(String personId) {
        return wcaRankSingleService.findWcaRankSinglesByPersonId(personId);
    }
    @RequestMapping("/findWcaRankSingleByPersonIdAndEventId")
    public WcaRankSingle findWcaRankSingleByPersonIdAndEventId(String personId, String eventId) {
        return wcaRankSingleService.findWcaRankSingleByPersonIdAndEventId(personId,eventId);
    }
}
