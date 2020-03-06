package xin.lz1998.wcads.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static xin.lz1998.wcads.domain.Event.MEGAMINX;
import static xin.lz1998.wcads.domain.Event.POCKET_CUBE;
import static xin.lz1998.wcads.domain.Event.RUBIKS_CUBE;
import static xin.lz1998.wcads.domain.Event.RUBIKS_CUBE_BLINDFOLDED;
import static xin.lz1998.wcads.domain.Gender.ALL;
import static xin.lz1998.wcads.domain.Gender.FEMALE;
import static xin.lz1998.wcads.domain.Gender.MALE;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
// These tests is based on the data released by WCA on March 1, 2020
public class Top10RankRepositoryTest {

    @Autowired
    private Top10RankRepository top10RankRepository;

    @Test
    public void shouldReturnTop10RubiksCubeSingleResultInChinaForMale() {
        // given
        Event event = RUBIKS_CUBE;
        String region = "China";
        Gender gender = MALE;

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10RankForCountryAndSingleResult(event, region, gender);

        // then
        assertThat(top10Rank).hasSize(10);
        assertThat(top10Rank.get(0).getBestResult()).isEqualTo(new BigDecimal("3.47"));
        assertThat(top10Rank.get(0).getPlayerName()).isEqualTo("Yusheng Du (杜宇生)");
        assertThat(top10Rank.get(9).getBestResult()).isEqualTo(new BigDecimal("5.77"));
        assertThat(top10Rank.get(9).getPlayerName()).isEqualTo("Dawei Xu (徐大卫)");
    }

    @Test
    public void shouldReturnTop10TwoByTwoAverageResultInUSAForFemale() {
        // given
        Event event = POCKET_CUBE;
        String region = "USA";
        Gender gender = FEMALE;

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10RankForCountryAndAverageResult(event, region, gender);

        // then
        assertThat(top10Rank).hasSize(10);
        assertThat(top10Rank.get(0).getBestResult()).isEqualTo(new BigDecimal("1.76"));
        assertThat(top10Rank.get(0).getPlayerName()).isEqualTo("Kymberlyn Calderon");
        assertThat(top10Rank.get(9).getBestResult()).isEqualTo(new BigDecimal("3.09"));
        assertThat(top10Rank.get(9).getPlayerName()).isEqualTo("Isabella Corona");
    }

    @Test
    public void shouldReturnTop10MinxAverageResultInSouthKoreanForAllGender() {
        // given
        Event event = MEGAMINX;
        String region = "Korea";
        Gender gender = ALL;

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10RankForCountryAndAverageResult(event, region, gender);

        // then
        assertThat(top10Rank).hasSize(10);
        assertThat(top10Rank.get(0).getBestResult()).isEqualTo(new BigDecimal("32.03"));
        assertThat(top10Rank.get(0).getPlayerName()).isEqualTo("Yu Da-Hyun (유다현)");
        assertThat(top10Rank.get(9).getBestResult()).isEqualTo(new BigDecimal("52.43"));
        assertThat(top10Rank.get(9).getPlayerName()).isEqualTo("Kim Min (김민)");
    }

    @Test
    public void shouldReturnTop10RubiksCubeBlindfoldedSingleResultForWholeWorldForAllGender() {
        // given
        Event event = RUBIKS_CUBE_BLINDFOLDED;
        String region = "wr";
        Gender gender = ALL;

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10RankSingleResultForWholeWorld(event, gender);

        // then
        assertThat(top10Rank).hasSize(10);
        assertThat(top10Rank.get(0).getBestResult()).isEqualTo(new BigDecimal("15.50"));
        assertThat(top10Rank.get(0).getPlayerName()).isEqualTo("Max Hilliard");
        assertThat(top10Rank.get(9).getBestResult()).isEqualTo(new BigDecimal("17.52"));
        assertThat(top10Rank.get(9).getPlayerName()).isEqualTo("Kaijun Lin (林恺俊)");
    }

}
