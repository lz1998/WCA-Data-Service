package xin.lz1998.wcads.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class PersonResultDTO {
    private String eventId;
    private Long best;

    @QueryProjection
    public PersonResultDTO(String eventId, Long best) {
        this.eventId = eventId;
        this.best = best;
    }
}
