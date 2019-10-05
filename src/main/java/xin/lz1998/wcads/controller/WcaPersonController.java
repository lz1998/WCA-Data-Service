package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.entity.WcaPerson;
import xin.lz1998.wcads.service.WcaPersonService;

import java.util.List;

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
    @RequestMapping("/findWcaPersonById")
    public Object findWcaPersonById(String id){
        return wcaPersonService.findWcaPersonById(id);
    }



    @RequestMapping("/findWcaPeopleByNameContaining")
    public List<WcaPerson> findWcaPeopleByNameContaining(String name) {
        return wcaPersonService.findWcaPeopleByNameContaining(name);
    }

}
