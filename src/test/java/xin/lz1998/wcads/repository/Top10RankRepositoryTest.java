package xin.lz1998.wcads.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.entity.WcaRankSingle;
import xin.lz1998.wcads.entity.WcaRankSingleKey;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
        String event = "333";
        String region = "China";
        String type = "sin";
        String gender = "m";

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10Rank(event, region, type, gender);

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
        String event = "222";
        String region = "USA";
        String type = "avg";
        String gender = "f";

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10Rank(event, region, type, gender);

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
        String event = "minx";
        String region = "Korea";
        String type = "avg";
        String gender = "all";

        // then
        List<Top10ResultDTO.Top10ItemDTO> top10Rank = top10RankRepository.findTop10Rank(event, region, type, gender);

        // then
        assertThat(top10Rank).hasSize(10);
        assertThat(top10Rank.get(0).getBestResult()).isEqualTo(new BigDecimal("32.03"));
        assertThat(top10Rank.get(0).getPlayerName()).isEqualTo("Yu Da-Hyun (유다현)");
        assertThat(top10Rank.get(9).getBestResult()).isEqualTo(new BigDecimal("52.43"));
        assertThat(top10Rank.get(9).getPlayerName()).isEqualTo("Kim Min (김민)");
    }

}
