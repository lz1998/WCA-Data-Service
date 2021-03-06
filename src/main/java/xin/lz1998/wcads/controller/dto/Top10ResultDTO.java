package xin.lz1998.wcads.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    public static class Top10ItemDTO {
        private String playerName;
        private Integer bestResult;

        @QueryProjection
        public Top10ItemDTO(String playerName, Integer bestResult) {
            this.playerName = playerName;
            this.bestResult = bestResult;
        }
    }

}
