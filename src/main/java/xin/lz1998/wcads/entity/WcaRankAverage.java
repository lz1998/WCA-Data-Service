package xin.lz1998.wcads.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(WcaRankAverageKey.class)
@Table(name = "WCA_EXPORT_RANKSAVERAGE",
        indexes = {@Index(name = "index_ranks_average_person_id",columnList = "PERSONID")})
public class WcaRankAverage implements Serializable {

    @Id
    @Column(name = "PERSONID")
    private String personId;

    @Id
    @Column(name = "EVENTID")
    private String eventId;

    @Column(name = "BEST")
    private Integer best;

    @Column(name = "WORLDRANK")
    private Integer worldRank;

    @Column(name = "CONTINENTRANK")
    private Integer continentRank;

    @Column(name = "COUNTRYRANK")
    private Integer countryRank;

}
