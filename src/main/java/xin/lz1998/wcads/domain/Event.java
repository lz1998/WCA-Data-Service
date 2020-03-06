package xin.lz1998.wcads.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum Event {
    /**
     * 2x2x2 Cube
     */
    POCKET_CUBE("222"),
    /**
     * 3x3x3 Cube
     */
    RUBIKS_CUBE("333"),
    /**
     * 4x4x4 Cube
     */
    RUBIKS_REVENGE("444"),
    /**
     * 5x5x5 Cube
     */
    PROFESSORS_CUBE("555"),
    /**
     * 6x6x6 Cube
     */
    SIX_BY_SIX_CUBE("666"),
    /**
     * 7x7x7 Cube
     */
    SEVEN_BY_SEVEN_CUBE("777"),
    /**
     * 3x3x3 Blindfolded
     */
    RUBIKS_CUBE_BLINDFOLDED("333bf"),
    /**
     * 3x3x3 One-Handed
     */
    RUBIKS_CUBE_ONE_HANDED("333oh"),
    /**
     * 3x3x3 Fewest Moves
     */
    RUBIKS_CUBE_FEWEST_MOVES("333fm"),
    /**
     * Megaminx
     */
    MEGAMINX("minx"),
    /**
     * Pyraminx
     */
    PYRAMINX("pyram"),
    /**
     * Square-1
     */
    SQUARE_ONE("sq1"),
    /**
     * Clock
     */
    CLOCK("clock"),
    /**
     * Skewb
     */
    SKEWB("skewb"),
    /**
     * 4x4x4 Blindfolded
     */
    RUBIKS_REVENGE_BLINDFOLDED("444bf"),
    /**
     * 5x5x5 Blindfolded
     */
    PROFESSORS_CUBE_BLINDFOLDED("555bf"),
    /**
     * 3x3x3 Multi-Blind
     */
    RUBIKS_CUBE_MULTI_BLIND("333mbf"),
    /**
     * all event
     */
    ALL("all");

    private String briefName;

    @JsonCreator
    public static Event from(String briefName) {
        if (Objects.isNull(briefName)){
            return RUBIKS_CUBE;
        }
        return Arrays.stream(Event.values()).filter(event -> briefName.equals(event.getBriefName())).findFirst().orElse(RUBIKS_CUBE);
    }
}
