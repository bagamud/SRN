package ru.ic.information_portal.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Road {
    @Id
    int id;
    private int code;
    private int codeASU;
    private String region;
    private String district;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCodeASU() {
        return codeASU;
    }

    public void setCodeASU(int codeASU) {
        this.codeASU = codeASU;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
