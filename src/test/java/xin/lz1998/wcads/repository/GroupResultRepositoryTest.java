package xin.lz1998.wcads.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import xin.lz1998.wcads.controller.dto.PersonResultDTO;
import xin.lz1998.wcads.domain.ResultType;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class GroupResultRepositoryTest {

    @Autowired
    private GroupResultRepository groupResultRepository;

    @Test
    public void shouldReturnSingleResultOfPersonInDateRange() {
        // given
        String wcaId = "2016LIZH03";
        Date startTime = Date.from(Instant.ofEpochSecond(LocalDateTime.of(2017, 9, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC)));
        Date endTime = Date.from(Instant.ofEpochSecond(LocalDateTime.of(2021, 7, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC)));

        // then
        List<PersonResultDTO> results = groupResultRepository.findAllEventResultsByWcaIdAndDurationAndResultType(wcaId, startTime, endTime, ResultType.SINGLE);

        // then
        assertThat(results.get(0)).isEqualTo(PersonResultDTO.builder().eventId("222").best(430L).build());
        assertThat(results.get(1)).isEqualTo(PersonResultDTO.builder().eventId("333").best(1620L).build());
        assertThat(results.get(2)).isEqualTo(PersonResultDTO.builder().eventId("333fm").best(47L).build());
        assertThat(results.get(3)).isEqualTo(PersonResultDTO.builder().eventId("333oh").best(3185L).build());
        assertThat(results.get(4)).isEqualTo(PersonResultDTO.builder().eventId("clock").best(2149L).build());
        assertThat(results.get(5)).isEqualTo(PersonResultDTO.builder().eventId("pyram").best(612L).build());
        assertThat(results.get(6)).isEqualTo(PersonResultDTO.builder().eventId("skewb").best(1005L).build());
    }

}
