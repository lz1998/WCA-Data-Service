package xin.lz1998.wcads.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.lz1998.wcads.controller.dto.GroupResultRequest;
import xin.lz1998.wcads.service.GroupResultService;
import xin.lz1998.wcads.controller.dto.PersonResult;
import xin.lz1998.wcads.utils.ResultWrapperUtils;

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
    public Object getRank(@RequestBody GroupResultRequest groupResultRequest){
        List<PersonResult> result = groupResultService.getResult(groupResultRequest);

        // 因为zbot的 post的结果不能是array，只能jsonObject，所以套一层
        return ResultWrapperUtils.resultWrapper(result);
    }
}
