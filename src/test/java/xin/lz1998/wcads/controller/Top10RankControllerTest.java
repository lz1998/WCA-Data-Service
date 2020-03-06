package xin.lz1998.wcads.controller;

import com.google.common.collect.Lists;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;
import xin.lz1998.wcads.domain.ResultType;
import xin.lz1998.wcads.domain.converter.EventConverter;
import xin.lz1998.wcads.domain.converter.GenderConverter;
import xin.lz1998.wcads.domain.converter.ResultTypeConverter;
import xin.lz1998.wcads.repository.Top10RankRepository;
import xin.lz1998.wcads.service.Top10RankService;
import xin.lz1998.wcads.service.impl.Top10RankServiceImpl;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class Top10RankControllerTest extends MockMvcBaseTest {

    private Top10RankRepository top10RankRepository = mock(Top10RankRepository.class);

    private FormattingConversionService conversionService = new FormattingConversionService();

    @Before
    public void setUp() {
        conversionService.addConverter(new EventConverter());
        conversionService.addConverter(new ResultTypeConverter());
        conversionService.addConverter(new GenderConverter());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(
                new Top10RankController(new Top10RankServiceImpl(top10RankRepository)))
                .setConversionService(conversionService)
                .build();
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void shouldReturn400WhenEventIdIsAbsent() {
        given()
                .when()
                .get("/top10rank")
                .then()
                .statusCode(BAD_REQUEST.value());
    }

    @Test
    public void shouldReturnTop10RankInChinaForAllGenderWhenJustPassEventId() {
        doReturn(Lists.newArrayList(new Top10ResultDTO.Top10ItemDTO()))
                .when(top10RankRepository).findTop10Rank(any(), any(), any(), any());

        given()
                .when()
                .get("/top10rank?event=333")
                .then()
                .statusCode(OK.value())
                .contentType(JSON);

        verify(top10RankRepository).findTop10Rank(Event.RUBIKS_CUBE, "nr", ResultType.SINGLE, Gender.ALL);
    }
}
