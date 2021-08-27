package ru.ic.information_portal.entity;

import javax.persistence.*;

@Table(schema = "iport")
@Entity(name = "SFixTerm")
public class SFixTerm {

    @Id
    int id;

    @ManyToOne(targetEntity = Shortcoming.class, fetch = FetchType.EAGER)
    Shortcoming shortcoming;

    @ManyToOne(targetEntity = RoadCategory.class, fetch = FetchType.EAGER)
    RoadCategory roadCategory;

    long fixTerm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shortcoming getShortcoming() {
        return shortcoming;
    }

    public void setShortcoming(Shortcoming shortcoming) {
        this.shortcoming = shortcoming;
    }

    public RoadCategory getRoadCategory() {
        return roadCategory;
    }

    public void setRoadCategory(RoadCategory roadCategory) {
        this.roadCategory = roadCategory;
    }

    public long getFixTerm() {
        return fixTerm;
    }

    public void setFixTerm(long fixTerm) {
        this.fixTerm = fixTerm;
    }
}
