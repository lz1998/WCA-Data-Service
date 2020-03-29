package xin.lz1998.wcads.repository;

import xin.lz1998.wcads.controller.dto.PersonResultDTO;
import xin.lz1998.wcads.domain.ResultType;

import java.util.Date;
import java.util.List;

public interface GroupResultRepository {
    List<PersonResultDTO> findAllEventResultsByWcaIdAndDurationAndResultType(String wcaId, Date startTime, Date endTime, ResultType single);
}
