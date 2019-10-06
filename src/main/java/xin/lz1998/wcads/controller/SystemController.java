package xin.lz1998.wcads.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("*")
@RequestMapping("/system")
@RestController
public class SystemController {
    @RequestMapping("/gc")
    public String gc(){
        System.gc();
        return "ok";
    }
}
