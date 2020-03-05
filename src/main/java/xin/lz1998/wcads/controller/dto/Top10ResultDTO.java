package xin.lz1998.wcads.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Top10ResultDTO {
    @Builder.Default
    List<Top10ItemDTO> top10ItemDTOList = new ArrayList<>();

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Top10ItemDTO {
        private String playerName;
        private BigDecimal bestResult;

        @QueryProjection
        public Top10ItemDTO(String playerName, Integer bestResult) {
            this.playerName = playerName;
            this.bestResult = BigDecimal.valueOf(bestResult).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        }
    }

}
