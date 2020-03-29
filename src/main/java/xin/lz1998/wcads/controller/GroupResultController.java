package xin.lz1998.wcads.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.controller.dto.GroupResultRequest;
import xin.lz1998.wcads.service.GroupResultService;
import xin.lz1998.wcads.controller.dto.PersonResult;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/groupResult")
@RestController
public class GroupResultController {

    private GroupResultService groupResultService;

    public GroupResultController(GroupResultService groupResultService) {
        this.groupResultService = groupResultService;
    }

    @PostMapping
    public List<PersonResult> getRank(@RequestBody GroupResultRequest groupResultRequest){
        return groupResultService.getResult(groupResultRequest);
    }
}
