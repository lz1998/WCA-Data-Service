package xin.lz1998.wcads.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegionType {
    /**
     * world
     */
    WORLD,
    /**
     * continent
     */
    CONTINENT,
    /**
     * country
     */
    COUNTRY;
}
