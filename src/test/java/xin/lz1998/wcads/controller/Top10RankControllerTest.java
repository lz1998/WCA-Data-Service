package xin.lz1998.wcads.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.service.Top10RankService;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class Top10RankControllerTest extends MockMvcBaseTest {

    private Top10RankService top10RankService = spy(Top10RankService.class);

    @Before
    public void setUp() {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new Top10RankController(top10RankService)).build();
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
        doReturn(Top10ResultDTO.builder().build()).when(top10RankService).searchTop10Rank(any(), any(), any(), any());

        given()
                .when()
                .get("/top10rank?event=333")
                .then()
                .statusCode(OK.value())
                .contentType(JSON);

        verify(top10RankService).searchTop10Rank("333", "nr", "sin", "all");
    }
}
