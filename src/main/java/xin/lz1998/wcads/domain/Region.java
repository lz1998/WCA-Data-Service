package xin.lz1998.wcads.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Region {
    /**
     * world record
     */
    WORLD_RECORD("wr"),
    /**
     * nation record
     */
    NATION_RECORD("nr"),
    /**
     * asia record
     */
    ASIA_RECORD("asr"),
    /**
     * africa record
     */
    AFRICA_RECORD("afr"),
    /**
     * europe record
     */
    EUROPE_RECORD("er"),
    /**
     * north america record
     */
    NORTH_AMERICA_RECORD("nar"),
    /**
     * south america record
     */
    SOUTH_AMERICA_RECORD("sar"),
    /**
     * oceania record
     */
    OCEANIA_RECORD("ocr");

    private String briefName;
}
