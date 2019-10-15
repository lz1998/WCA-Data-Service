package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.Config;
import xin.lz1998.wcads.entity.*;
import xin.lz1998.wcads.service.WcaService;
import xin.lz1998.wcads.utils.DataImportUtil;

@CrossOrigin("*")
@RequestMapping("/wca")
@RestController
public class WcaController {
    // TODO 如果正在执行某件事，不执行新的事
    @Autowired
    WcaService wcaService;
    @RequestMapping("/importData")
    public Object importData(){
        wcaService.importData();
        return "ok";
    }
    @RequestMapping("/downloadData")
    public Object downloadData(){
        wcaService.downloadData();
        return "ok";
    }
    @RequestMapping("/extractData")
    public Object extractData(){
        wcaService.extractData();
        return "ok";
    }
    @RequestMapping("/updateData")
    public Object updateData() {
        wcaService.updateData();
        return "ok";
    }

    @RequestMapping("/importPersons")
    public Object importPersons() {
        wcaService.importPersons();
        return "ok";
    }
    @RequestMapping("/importRanksAverage")
    public Object importRanksAverage() {
        wcaService.importRanksAverage();
        return "ok";
    }
    @RequestMapping("/importRanksSingle")
    public Object importRanksSingle() {
        wcaService.importRanksSingle();
        return "ok";
    }
    @RequestMapping("/importResults")
    public Object importResults() {
        wcaService.importResults();
        return "ok";
    }
    @RequestMapping("/importCompetitions")
    public Object importCompetitions() {
        wcaService.importCompetitions();
        return "ok";
    }
}
