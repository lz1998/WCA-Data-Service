package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.service.WcaService;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@RequestMapping("/wca")
@RestController
public class WcaController {
    // TODO 如果正在执行某件事，不执行新的事
    @Autowired
    WcaService wcaService;
    @RequestMapping("/importData")
    public Object importData(){
        wcaService.importWcaData();
        return "ok";
    }
    @RequestMapping("/downloadData")
    public Object downloadData(){
        wcaService.downloadWcaData();
        return "ok";
    }
    @RequestMapping("/extractData")
    public Object extractData(){
        wcaService.extractWcaData();
        return "ok";
    }
    @RequestMapping("/updateData")
    public Object updateData() {
        wcaService.updateWcaData();
        return "ok";
    }
}
