package xin.lz1998.wcads.service.impl;

import org.springframework.stereotype.Service;
import xin.lz1998.wcads.controller.dto.GroupResultRequest;
import xin.lz1998.wcads.controller.dto.PersonResultDTO;
import xin.lz1998.wcads.domain.ResultType;
import xin.lz1998.wcads.repository.GroupResultRepository;
import xin.lz1998.wcads.service.GroupResultService;
import xin.lz1998.wcads.controller.dto.PersonResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupResultServiceImpl implements GroupResultService {

    private GroupResultRepository groupResultRepository;

    public GroupResultServiceImpl(GroupResultRepository groupResultRepository) {
        this.groupResultRepository = groupResultRepository;
    }

    @Override
    public List<PersonResult> getResult(GroupResultRequest groupResultRequest) {
        return groupResultRequest.getGroupResultRequestList().stream()
                .map(this::buildAllEventResultsForPerson)
                .collect(Collectors.toList());
    }

    private PersonResult buildAllEventResultsForPerson(GroupResultRequest.GroupResultRequestItem requestItem) {
        List<PersonResultDTO> allEventSingleResultsByWcaIdAndDuration = getPersonSingleResult(requestItem);
        List<PersonResultDTO> allEventAverageResultsByWcaIdAndDuration = getPersonAverageResult(requestItem);
        List<PersonResult.PersonResultItem> personResultItem = allEventSingleResultsByWcaIdAndDuration.stream()
                .map(personResultDTO -> buildResultForEvent(allEventAverageResultsByWcaIdAndDuration, personResultDTO))
                .collect(Collectors.toList());
        return PersonResult.builder().wcaId(requestItem.getWcaId()).personResultItemList(personResultItem).build();
    }

    private PersonResult.PersonResultItem buildResultForEvent(List<PersonResultDTO> averageResults, PersonResultDTO singleResult) {
        PersonResultDTO averageResultForEvent = averageResults.stream().filter(averageResult ->
                singleResult.getEventId().equals(averageResult.getEventId())
        ).findFirst().orElse(new PersonResultDTO());
        return PersonResult.PersonResultItem.builder()
                .eventId(singleResult.getEventId())
                .singleResult(singleResult.getBest())
                .averageResult(averageResultForEvent.getBest())
                .build();
    }

    private List<PersonResultDTO> getPersonSingleResult(GroupResultRequest.GroupResultRequestItem requestItem) {
        return groupResultRepository.findAllEventResultsByWcaIdAndDurationAndResultType(
                requestItem.getWcaId(),
                requestItem.getStartTime(),
                requestItem.getEndTime(),
                ResultType.SINGLE
        );
    }

    private List<PersonResultDTO> getPersonAverageResult(GroupResultRequest.GroupResultRequestItem requestItem) {
        return groupResultRepository.findAllEventResultsByWcaIdAndDurationAndResultType(
                requestItem.getWcaId(),
                requestItem.getStartTime(),
                requestItem.getEndTime(),
                ResultType.AVERAGE
        );
    }
}
