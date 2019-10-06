package xin.lz1998.wcads.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "WCA_EXPORT_RANKSAVERAGE")
public class WcaRankAverage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PERSONID")
    private String personId;

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
