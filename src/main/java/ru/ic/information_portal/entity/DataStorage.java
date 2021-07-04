package ru.ic.information_portal.entity;

import org.hibernate.type.BlobType;

import javax.persistence.*;
import java.sql.Blob;

@Entity
public class DataStorage {
    @Id
    int id;

    @ManyToOne(targetEntity = StreetRoadNetwork.class, fetch = FetchType.LAZY)
    StreetRoadNetwork srn;

    @Lob
    Blob file;

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

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {
        this.file = file;
    }
}
