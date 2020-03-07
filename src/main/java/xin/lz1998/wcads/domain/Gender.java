package xin.lz1998.wcads.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import xin.lz1998.wcads.exception.UnknownGenderException;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum Gender {
    /**
     * male
     */
    MALE("m"),
    /**
     * female
     */
    FEMALE("f"),
    /**
     * all gender
     */
    ALL("all");

    private String briefName;

    @JsonCreator
    public static Gender from(String briefName) {
        if (Objects.isNull(briefName)) {
            throw new UnknownGenderException();
        }
        return Arrays.stream(Gender.values()).filter(gender -> briefName.equalsIgnoreCase(gender.getBriefName()))
                .findFirst().orElseThrow(() -> new UnknownGenderException(briefName));
    }
}
