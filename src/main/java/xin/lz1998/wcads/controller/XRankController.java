package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.service.XRankService;

import java.util.Arrays;

@CrossOrigin("*")
@RequestMapping("/xRank")
@RestController
public class XRankController {

    @Autowired
    XRankService xRankService;
    @RequestMapping("/getRank")
    public Object getRank(String wcaIds){
        // 统计某魔方社团排名
        String[] wcaIdArray =wcaIds.split(",");
        return xRankService.getRank(Arrays.asList(wcaIdArray));
    }
}
