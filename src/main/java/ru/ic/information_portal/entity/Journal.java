package ru.ic.information_portal.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(schema = "iport")
@Entity(name = "Journal")
public class Journal {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Id
    private int id;

    @JoinColumn
    @ManyToOne(targetEntity = StreetRoadNetwork.class)
    private StreetRoadNetwork srn;

    @Column(columnDefinition = "JSON")
    private String srnJson;

    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp entryDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StreetRoadNetwork getSrn() {
        return srn;
    }

    public void setSrn(StreetRoadNetwork srn) {
        this.srn = srn;
    }

    public String getSrnJson() {
        return srnJson;
    }

    public void setSrnJson(String srnJson) {
        this.srnJson = srnJson;
    }

    public Timestamp getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Timestamp entryDate) {
        this.entryDate = entryDate;
    }

}
