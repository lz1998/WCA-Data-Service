package xin.lz1998.wcads.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "WCA_EXPORT_COMPETITIONS")
public class WcaCompetition {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CITYNAME")
    private String cityName;

    @Column(name = "COUNTRYID")
    private String countryId;

    @Column(name = "INFORMATION")
    private String information;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "MONTH")
    private Integer month;

    @Column(name = "DAY")
    private Integer day;

    @Column(name = "ENDMONTH")
    private Integer endMonth;

    @Column(name = "ENDDAY")
    private Integer endDay;

    @Column(name = "EVENTSPECS")
    private String eventSpecs;

    @Column(name = "WCADELEGATE")
    private String wcaDelegate;

    @Column(name = "ORGANISER")
    private String organiser;

    @Column(name = "VENUE")
    private String venue;

    @Column(name = "VENUEADDRESS")
    private String venueAddress;

    @Column(name = "VENUEDETAILS")
    private String venueDetails;

    @Column(name = "EXTERNAL_WEBSITE")
    private String externalWebsite;

    @Column(name = "CELLNAME")
    private String cellName;

    @Column(name = "LATITUDE")
    private Integer latitude;

    @Column(name = "LONGITUDE")
    private Integer longitude;

}
