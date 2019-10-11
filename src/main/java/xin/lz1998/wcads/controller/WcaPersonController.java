package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.entity.WcaPerson;
import xin.lz1998.wcads.service.WcaPersonService;

import java.util.Arrays;
import java.util.List;
@CrossOrigin("*")
@RequestMapping("/wcaPerson")
@RestController
public class WcaPersonController {
    @Autowired
    private WcaPersonService wcaPersonService;
    @RequestMapping("/importData")
    public Object importData(){
        wcaPersonService.importData();
        return "ok";
    }
    @RequestMapping("/findPersonById")
    public Object findPersonById(String id){
        return wcaPersonService.findPersonById(id);
    }


    @RequestMapping("/searchPeople")
    public List<WcaPerson> searchPeople(String q) {
        String[] keywordArray=q.split(" ");
        return wcaPersonService.searchPeople(Arrays.asList(keywordArray));
    }

}
