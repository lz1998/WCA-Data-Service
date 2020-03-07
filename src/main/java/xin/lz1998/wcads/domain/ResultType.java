package xin.lz1998.wcads.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import xin.lz1998.wcads.exception.UnknownResultTypeException;

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
        if (Objects.isNull(briefName)) {
            throw new UnknownResultTypeException();
        }
        return Arrays.stream(ResultType.values()).filter(resultType -> briefName.equalsIgnoreCase(resultType.getBriefName()))
                .findFirst().orElseThrow(() -> new UnknownResultTypeException(briefName));
    }
}
