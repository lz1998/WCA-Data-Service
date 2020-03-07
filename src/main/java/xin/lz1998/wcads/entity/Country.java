package xin.lz1998.wcads.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "COUNTRIES")
public class Country {

    @Id
    private String id;

    private String name;

    @Column(name = "CONTINENTID")
    private String continentId;

    private String iso2;
}
