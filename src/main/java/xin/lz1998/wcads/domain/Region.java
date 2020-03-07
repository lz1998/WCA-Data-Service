package xin.lz1998.wcads.domain;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

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

    protected static final List<Region> CONTINENT_RECORD = Lists.newArrayList(
            ASIA_RECORD, AFRICA_RECORD, EUROPE_RECORD, NORTH_AMERICA_RECORD, SOUTH_AMERICA_RECORD, OCEANIA_RECORD);

    private String briefName;

    public static boolean isWorldRecord(String region) {
        return region.equalsIgnoreCase(WORLD_RECORD.getBriefName());
    }

    public static boolean isContinentRecord(String region) {
        return CONTINENT_RECORD.stream().anyMatch(record -> record.getBriefName().equalsIgnoreCase(region));
    }

    public static boolean isCountryRecord(String region) {
        // region that is not wr or continent record will be considered as nr or nation name
        return !Region.isContinentRecord(region) && !Region.isWorldRecord(region);
    }
}
