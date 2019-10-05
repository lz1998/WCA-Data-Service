package xin.lz1998.wcads.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "WCA_EXPORT_PERSONS")
public class WcaPerson {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "SUBID")
    private Integer subId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COUNTRYID")
    private String countryId;

    @Column(name = "GENDER")
    private String gender;
}
