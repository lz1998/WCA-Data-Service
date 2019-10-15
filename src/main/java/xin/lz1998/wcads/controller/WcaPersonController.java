package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.entity.WcaPerson;
import xin.lz1998.wcads.service.WcaPersonService;
import xin.lz1998.wcads.utils.PageUtil;
import xin.lz1998.wcads.utils.ResultWrapperUtils;

import java.util.Arrays;
import java.util.List;
@CrossOrigin("*")
@RequestMapping("/wcaPerson")
@RestController
public class WcaPersonController {
    @Autowired
    private WcaPersonService wcaPersonService;
    @RequestMapping("/findPersonById")
    public Object findPersonById(String id){
        WcaPerson data=wcaPersonService.findPersonById(id);
        return ResultWrapperUtils.resultWrapper(data);
    }


    @RequestMapping("/searchPeople")
    public Object searchPeople(String q,Integer pageNum,Integer pageSize) {
        String[] keywordArray=q.split(" ");
        List<String> stringList = Arrays.asList(keywordArray);
        Pageable pageable= PageUtil.getPageable(pageNum,pageSize);
        Page data = wcaPersonService.searchPeople(stringList, pageable);

        return ResultWrapperUtils.pageResultWrapper(data);
    }

}
