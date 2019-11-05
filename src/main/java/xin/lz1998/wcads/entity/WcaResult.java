package xin.lz1998.wcads.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(WcaResultKey.class)
@Table(name = "WCA_EXPORT_RESULTS",
        indexes = {@Index(name = "index_results_person_id",columnList = "PERSONID")})
public class WcaResult implements Serializable {
    @Id
    @Column(name = "COMPETITIONID")
    private String competitionId;
    @Id
    @Column(name = "EVENTID")
    private String eventId;
    @Id
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

    @Id
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
