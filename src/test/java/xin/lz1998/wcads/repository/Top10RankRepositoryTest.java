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
        assertThat(top10Rank.get(9).getBestResult()).isEqualTo(new BigDecimal("5.66"));
        assertThat(top10Rank.get(9).getPlayerName()).isEqualTo("Mulun Yin (阴目仑)");
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
        assertThat(top10Rank.get(0).getBestResult()).isEqualTo(new BigDecimal("11.98"));
        assertThat(top10Rank.get(0).getPlayerName()).isEqualTo("Uma Unni");
        assertThat(top10Rank.get(9).getBestResult()).isEqualTo(new BigDecimal("3.06"));
        assertThat(top10Rank.get(9).getPlayerName()).isEqualTo("Melissa Su");
    }

}
