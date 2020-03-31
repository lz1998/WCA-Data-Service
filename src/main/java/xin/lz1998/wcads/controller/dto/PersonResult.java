package xin.lz1998.wcads.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonResult {
    private String wcaId;
    private List<PersonResultItem> personResultItemList;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PersonResultItem {
        private String eventId;
        private Long singleResult;
        private Long averageResult;
    }
}
