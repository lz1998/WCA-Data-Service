package xin.lz1998.wcads.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "CONTINENTS")
public class Continent {

    @Id
    private String id;

    private String name;

    @Column(name = "RECORDNAME")
    private String recordName;

    private String latitude;

    private String longitude;

    private String zoom;

}
