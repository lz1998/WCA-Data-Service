package xin.lz1998.wcads.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupResultRequest {

    private List<GroupResultRequestItem> groupResultRequestList;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GroupResultRequestItem {
        private String wcaId;
        private Date startTime;
        private Date endTime;
    }
}
