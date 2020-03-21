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
import xin.lz1998.wcads.domain.ResultType;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static xin.lz1998.wcads.domain.Event.MEGAMINX;
import static xin.lz1998.wcads.domain.Event.POCKET_CUBE;
import static xin.lz1998.wcads.domain.Event.RUBIKS_CUBE;
import static xin.lz1998.wcads.domain.Event.RUBIKS_CUBE_BLINDFOLDED;
import static xin.lz1998.wcads.domain.Event.RUBIKS_CUBE_FEWEST_MOVES;
import static xin.lz1998.wcads.domain.Event.RUBIKS_CUBE_MULTI_BLIND;
import static xin.lz1998.wcads.domain.Event.RUBIKS_CUBE_ONE_HANDED;
import static xin.lz1998.wcads.domain.Event.SIX_BY_SIX_CUBE;
import static xin.lz1998.wcads.domain.Gender.ALL;
import static xin.lz1998.wcads.domain.Gender.FEMALE;
import static xin.lz1998.wcads.domain.Gender.MALE;
import static xin.lz1998.wcads.domain.Region.ASIA_RECORD;
import static xin.lz1998.wcads.domain.Region.NORTH_AMERICA_RECORD;
import static xin.lz1998.wcads.domain.Region.OCEANIA_RECORD;
import static xin.lz1998.wcads.domain.Region.WORLD_RECORD;
import static xin.lz1998.wcads.domain.ResultType.AVERAGE;
import static xin.lz1998.wcads.domain.ResultType.SINGLE;

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
        ResultType type = SINGLE;
        String region = "China";
        Gender gender = MALE;

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10RankForCountry(event, region, type, gender);

        // then
        assertThat(top10Rank).hasSize(10);
        assertThat(top10Rank.get(0).getBestResult()).isEqualTo(347);
        assertThat(top10Rank.get(0).getPlayerName()).isEqualTo("Yusheng Du (杜宇生)");
        assertThat(top10Rank.get(9).getBestResult()).isEqualTo(577);
        assertThat(top10Rank.get(9).getPlayerName()).isEqualTo("Dawei Xu (徐大卫)");
    }

    @Test
    public void shouldReturnTop10TwoByTwoAverageResultInUSAForFemale() {
        // given
        Event event = POCKET_CUBE;
        ResultType type = AVERAGE;
        String region = "USA";
        Gender gender = FEMALE;

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10RankForCountry(event, region, type, gender);

        // then
        assertThat(top10Rank).hasSize(10);
        assertThat(top10Rank.get(0).getBestResult()).isEqualTo(176);
        assertThat(top10Rank.get(0).getPlayerName()).isEqualTo("Kymberlyn Calderon");
        assertThat(top10Rank.get(9).getBestResult()).isEqualTo(309);
        assertThat(top10Rank.get(9).getPlayerName()).isEqualTo("Isabella Corona");
    }

    @Test
    public void shouldReturnTop10MinxAverageResultInSouthKoreanForAllGender() {
        // given
        Event event = MEGAMINX;
        ResultType type = AVERAGE;
        String region = "Korea";
        Gender gender = ALL;

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10RankForCountry(event, region, type, gender);

        // then
        assertThat(top10Rank).hasSize(10);
        assertThat(top10Rank.get(0).getBestResult()).isEqualTo(3203);
        assertThat(top10Rank.get(0).getPlayerName()).isEqualTo("Yu Da-Hyun (유다현)");
        assertThat(top10Rank.get(9).getBestResult()).isEqualTo(5243);
        assertThat(top10Rank.get(9).getPlayerName()).isEqualTo("Kim Min (김민)");
    }

    @Test
    public void shouldReturnTop10RubiksCubeBlindfoldedSingleResultForWholeWorldForAllGender() {
        // given
        Event event = RUBIKS_CUBE_BLINDFOLDED;
        ResultType type = SINGLE;
        String region = "wr";
        Gender gender = ALL;

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10RankForWholeWorld(event, type, gender);

        // then
        assertThat(top10Rank).hasSize(10);
        assertThat(top10Rank.get(0).getBestResult()).isEqualTo(1550);
        assertThat(top10Rank.get(0).getPlayerName()).isEqualTo("Max Hilliard");
        assertThat(top10Rank.get(9).getBestResult()).isEqualTo(1752);
        assertThat(top10Rank.get(9).getPlayerName()).isEqualTo("Kaijun Lin (林恺俊)");
    }

    @Test
    public void shouldReturnTop10RubiksCubeOneHandAverageResultForAsiaForMale() {
        // given
        Event event = RUBIKS_CUBE_ONE_HANDED;
        ResultType type = AVERAGE;
        String region = ASIA_RECORD.getBriefName();
        Gender gender = MALE;

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10RankForContinent(event, region, type, gender);

        // then
        assertThat(top10Rank).hasSize(10);
        assertThat(top10Rank.get(0).getBestResult()).isEqualTo(1020);
        assertThat(top10Rank.get(0).getPlayerName()).isEqualTo("Bhargav Narasimhan");
        assertThat(top10Rank.get(9).getBestResult()).isEqualTo(1151);
        assertThat(top10Rank.get(9).getPlayerName()).isIn("Zaiyang Zhang (张在旸)", "Lin Chen (陈霖)");
    }

    @Test
    public void shouldReturnTop10SixBySixSingleResultForNorthAmericaForAllGender() {
        // given
        Event event = SIX_BY_SIX_CUBE;
        ResultType type = SINGLE;
        String region = NORTH_AMERICA_RECORD.getBriefName();
        Gender gender = ALL;

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10RankForContinent(event, region, type, gender);

        // then
        assertThat(top10Rank).hasSize(10);
        assertThat(top10Rank.get(0).getBestResult()).isEqualTo(6951);
        assertThat(top10Rank.get(0).getPlayerName()).isEqualTo("Max Park");
        assertThat(top10Rank.get(9).getBestResult()).isEqualTo(9233);
        assertThat(top10Rank.get(9).getPlayerName()).isEqualTo("Pahul Singh");
    }

    @Test
    public void shouldReturnTop10RubiksCubeMultiBlindfoldSingleResultForOceaniaForAllGender() {
        // given
        Event event = RUBIKS_CUBE_MULTI_BLIND;
        ResultType type = SINGLE;
        String region = OCEANIA_RECORD.getBriefName();
        Gender gender = ALL;

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10RankForContinent(event, region, type, gender);

        // then
        assertThat(top10Rank).hasSize(10);
        assertThat(top10Rank.get(0).getBestResult()).isEqualTo(710358001);
        assertThat(top10Rank.get(0).getPlayerName()).isEqualTo("Jack Cai");
        assertThat(top10Rank.get(9).getBestResult()).isEqualTo(860301201);
        assertThat(top10Rank.get(9).getPlayerName()).isEqualTo("Liam Sweet");
    }

    @Test
    public void shouldReturnEmptyTop10RubiksCubeMultiBlindfoldAverageResultForWholeWorldForAllGender() {
        // given
        Event event = RUBIKS_CUBE_MULTI_BLIND;
        ResultType type = AVERAGE;
        String region = WORLD_RECORD.getBriefName();
        Gender gender = ALL;

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10RankForWholeWorld(event, type, gender);

        // then
        assertThat(top10Rank).isEmpty();
    }

    @Test
    public void shouldReturnAllTop10RubiksCubeFewestMovesAverageResultForWholeWorldForAllGender() {
        // given
        Event event = RUBIKS_CUBE_FEWEST_MOVES;
        ResultType type = AVERAGE;
        String region = WORLD_RECORD.getBriefName();
        Gender gender = ALL;

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10RankForWholeWorld(event, type, gender);

        // then
        assertThat(top10Rank).hasSize(11);
        assertThat(top10Rank.get(0).getBestResult()).isEqualTo(2100);
        assertThat(top10Rank.get(0).getPlayerName()).isEqualTo("Cale Schoon");
        assertThat(top10Rank.get(9).getBestResult()).isEqualTo(2367);
        assertThat(top10Rank.get(9).getPlayerName()).isIn("Baiqiang Dong (董百强)", "Dávid Balog");
    }

}
