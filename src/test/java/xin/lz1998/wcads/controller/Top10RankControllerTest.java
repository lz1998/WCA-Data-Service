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
import xin.lz1998.wcads.service.impl.rank.ContinentTop10RankServiceImpl;
import xin.lz1998.wcads.service.impl.rank.CountryTop10RankServiceImpl;
import xin.lz1998.wcads.service.impl.rank.Top10RankServiceAdapter;
import xin.lz1998.wcads.service.impl.rank.WorldTop10RankServiceImpl;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static xin.lz1998.wcads.domain.Region.ASIA_RECORD;

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
                new Top10RankController(new Top10RankServiceAdapter(Lists.newArrayList(
                        new WorldTop10RankServiceImpl(top10RankRepository),
                        new ContinentTop10RankServiceImpl(top10RankRepository),
                        new CountryTop10RankServiceImpl(top10RankRepository))
                ))
        )
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
                .when(top10RankRepository).findTop10RankForCountry(any(), any(), any(), any());

        given()
                .when()
                .get("/top10rank?event=333")
                .then()
                .statusCode(OK.value())
                .contentType(JSON);

        verify(top10RankRepository).findTop10RankForCountry(Event.RUBIKS_CUBE, "China", ResultType.SINGLE, Gender.ALL);
    }

    @Test
    public void shouldReturnTop10RankForWholeWorldForAllGender() {
        doReturn(Lists.newArrayList(new Top10ResultDTO.Top10ItemDTO()))
                .when(top10RankRepository).findTop10RankForWholeWorld(any(), any(), any());

        given()
                .when()
                .get("/top10rank?event=333&region=wr&type=avg")
                .then()
                .statusCode(OK.value())
                .contentType(JSON);

        verify(top10RankRepository).findTop10RankForWholeWorld(Event.RUBIKS_CUBE, ResultType.AVERAGE, Gender.ALL);
    }

    @Test
    public void shouldReturnTop10RankForAsiaForAllGender() {
        doReturn(Lists.newArrayList(new Top10ResultDTO.Top10ItemDTO()))
                .when(top10RankRepository).findTop10RankForContinent(any(), any(), any(), any());

        given()
                .when()
                .get("/top10rank?event=333oh&region=asr")
                .then()
                .statusCode(OK.value())
                .contentType(JSON);

        verify(top10RankRepository).findTop10RankForContinent(Event.RUBIKS_CUBE_ONE_HANDED, ASIA_RECORD.getBriefName(), ResultType.SINGLE, Gender.ALL);
    }

    @Test
    public void shouldReturnTop10RankForIndiaForAllGender() {
        doReturn(Lists.newArrayList(new Top10ResultDTO.Top10ItemDTO()))
                .when(top10RankRepository).findTop10RankForCountry(any(), any(), any(), any());

        given()
                .when()
                .get("/top10rank?event=555&region=india")
                .then()
                .statusCode(OK.value())
                .contentType(JSON);

        verify(top10RankRepository).findTop10RankForCountry(Event.PROFESSORS_CUBE, "india", ResultType.SINGLE, Gender.ALL);
    }

    @Test
    public void shouldReturnTop10RankIgnoreCase() {
        doReturn(Lists.newArrayList(new Top10ResultDTO.Top10ItemDTO()))
                .when(top10RankRepository).findTop10RankForCountry(any(), any(), any(), any());

        given()
                .when()
                .get("/top10rank?event=Pyram&region=NR&type=AVG&gender=F")
                .then()
                .statusCode(OK.value())
                .contentType(JSON);

        verify(top10RankRepository).findTop10RankForCountry(Event.PYRAMINX, "China", ResultType.AVERAGE, Gender.FEMALE);
    }
}
