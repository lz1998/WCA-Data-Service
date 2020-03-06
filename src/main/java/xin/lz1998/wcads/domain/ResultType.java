package xin.lz1998.wcads.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ResultType {
    /**
     * single result
     */
    SINGLE("sin"),
    /**
     * average result
     */
    AVERAGE("avg");

    private String briefName;

    @JsonCreator
    public static ResultType from(String briefName) {
        if (Objects.isNull(briefName)){
            return SINGLE;
        }
        return Arrays.stream(ResultType.values()).filter(resultType -> briefName.equals(resultType.getBriefName())).findFirst().orElse(SINGLE);
    }
}
