package xin.lz1998.wcads.service;

import xin.lz1998.wcads.controller.dto.GroupResultRequest;
import xin.lz1998.wcads.controller.dto.PersonResult;

import java.util.List;

public interface GroupResultService {
    List<PersonResult> getResult(GroupResultRequest groupResultRequest);
}
