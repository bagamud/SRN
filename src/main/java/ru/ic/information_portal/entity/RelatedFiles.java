package ru.ic.information_portal.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class RelatedFiles {
    @Id
    private int id;
    private int srn;
    private String fileName;
    private String loadUserName;

    private Timestamp loadDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSrn() {
        return srn;
    }

    public void setSrn(int srn) {
        this.srn = srn;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLoadUserName() {
        return loadUserName;
    }

    public void setLoadUserName(String loadUserName) {
        this.loadUserName = loadUserName;
    }

    public Timestamp getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(Timestamp loadDate) {
        this.loadDate = loadDate;
    }
}