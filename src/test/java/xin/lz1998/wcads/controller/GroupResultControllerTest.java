//package xin.lz1998.wcads.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.restassured.module.mockmvc.RestAssuredMockMvc;
//import org.assertj.core.util.Lists;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import xin.lz1998.wcads.controller.dto.GroupResultRequest;
//import xin.lz1998.wcads.controller.dto.PersonResult;
//import xin.lz1998.wcads.controller.dto.PersonResultDTO;
//import xin.lz1998.wcads.repository.GroupResultRepository;
//import xin.lz1998.wcads.service.impl.GroupResultServiceImpl;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.mock;
//import static org.springframework.http.HttpStatus.OK;
//import static xin.lz1998.wcads.domain.ResultType.AVERAGE;
//import static xin.lz1998.wcads.domain.ResultType.SINGLE;
//
//@RunWith(MockitoJUnitRunner.class)
//public class GroupResultControllerTest extends MockMvcBaseTest {
//
//    private GroupResultRepository groupResultRepository = mock(GroupResultRepository.class);
//
//    @Before
//    public void setUp() {
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(
//                new GroupResultController(new GroupResultServiceImpl(groupResultRepository))
//        ).build();
//        RestAssuredMockMvc.mockMvc(mockMvc);
//    }
//
//    @Test
//    public void shouldReturnPersonResult() throws JsonProcessingException {
//        Date startTime = Date.from(Instant.ofEpochSecond(LocalDateTime.of(2017, 9, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC)));
//        Date endTime = Date.from(Instant.ofEpochSecond(LocalDateTime.of(2021, 7, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC)));
//        String wcaId = "2016LIZH03";
//        GroupResultRequest.GroupResultRequestItem groupResultRequestItem = GroupResultRequest.GroupResultRequestItem.builder()
//                .wcaId(wcaId).startTime(startTime).endTime(endTime).build();
//        GroupResultRequest groupResultRequest = GroupResultRequest.builder().build();
//        groupResultRequest.setGroupResultRequestList(Lists.newArrayList(groupResultRequestItem));
//
//        PersonResultDTO singleResultOf222 = PersonResultDTO.builder().eventId("222").best(430L).build();
//        PersonResultDTO averageResultOf222 = PersonResultDTO.builder().eventId("222").best(561L).build();
//        PersonResultDTO singleResultOf333 = PersonResultDTO.builder().eventId("333").best(1620L).build();
//        List<PersonResultDTO> singleResults = Lists.newArrayList(singleResultOf222, singleResultOf333);
//        List<PersonResultDTO> averageResults = Lists.newArrayList(averageResultOf222);
//        doReturn(singleResults).when(groupResultRepository).findAllEventResultsByWcaIdAndDurationAndResultType(wcaId, startTime, endTime, SINGLE);
//        doReturn(averageResults).when(groupResultRepository).findAllEventResultsByWcaIdAndDurationAndResultType(wcaId, startTime, endTime, AVERAGE);
//
//        PersonResult.PersonResultItem resultOf222 = PersonResult.PersonResultItem.builder().eventId("222").singleResult(430L).averageResult(561L).build();
//        PersonResult.PersonResultItem resultOf333 = PersonResult.PersonResultItem.builder().eventId("333").singleResult(1620L).build();
//        PersonResult personResult = PersonResult.builder().wcaId(wcaId).personResultItem(Lists.newArrayList(resultOf222, resultOf333)).build();
//        String response = new ObjectMapper().writeValueAsString(Lists.newArrayList(personResult));
//
//        given()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(groupResultRequest)
//                .when()
//                .post("/groupResult")
//                .then()
//                .statusCode(OK.value())
//                .body(is(response));
//    }
//
//}
