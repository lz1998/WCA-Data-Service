package xin.lz1998.wcads.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "WCA_EXPORT_RESULTS",
        indexes = {@Index(name = "index_person_id",columnList = "PERSONID")})
public class WcaResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COMPETITIONID")
    private String competitionId;

    @Column(name = "EVENTID")
    private String eventId;

    @Column(name = "ROUNDTYPEID")
    private String roundTypeId;

    @Column(name = "POS")
    private Long pos;

    @Column(name = "BEST")
    private Long best;

    @Column(name = "AVERAGE")
    private Long average;

    @Column(name = "PERSONNAME")
    private String personName;

    @Column(name = "PERSONID")
    private String personId;

    @Column(name = "PERSONCOUNTRYID")
    private String personCountryId;

    @Column(name = "FORMATID")
    private String formatId;

    @Column(name = "VALUE1")
    private Long value1;

    @Column(name = "VALUE2")
    private Long value2;

    @Column(name = "VALUE3")
    private Long value3;

    @Column(name = "VALUE4")
    private Long value4;

    @Column(name = "VALUE5")
    private Long value5;

    @Column(name = "REGIONALSINGLERECORD")
    private String regionalSingleRecord;

    @Column(name = "REGIONALAVERAGERECORD")
    private String regionalAverageRecord;


}
