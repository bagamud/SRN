package ru.ic.information_portal.reports;

import ru.ic.information_portal.entity.*;

import java.sql.Date;

public class FormRequest {

    private Date createDate;
    private Department department;
    private String foundWho;
    private String foundPlace;
    private Date foundPeriodStart;
    private Date foundPeriodEnd;
    private Shortcoming shortcoming;
    private Devices devices;
    private Measures measures;
    private Date measuresDate;
    private Status status;
    private Date closeDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getFoundWho() {
        return foundWho;
    }

    public void setFoundWho(String foundWho) {
        this.foundWho = foundWho;
    }

    public String getFoundPlace() {
        return foundPlace;
    }

    public void setFoundPlace(String foundPlace) {
        this.foundPlace = foundPlace;
    }

    public Date getFoundPeriodStart() {
        return foundPeriodStart;
    }

    public void setFoundPeriodStart(Date foundPeriodStart) {
        this.foundPeriodStart = foundPeriodStart;
    }

    public Date getFoundPeriodEnd() {
        return foundPeriodEnd;
    }

    public void setFoundPeriodEnd(Date foundPeriodEnd) {
        this.foundPeriodEnd = foundPeriodEnd;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Shortcoming getShortcoming() {
        return shortcoming;
    }

    public void setShortcoming(Shortcoming shortcoming) {
        this.shortcoming = shortcoming;
    }

    public Devices getDevices() {
        return devices;
    }

    public void setDevices(Devices devices) {
        this.devices = devices;
    }

    public Measures getMeasures() {
        return measures;
    }

    public void setMeasures(Measures measures) {
        this.measures = measures;
    }

    public Date getMeasuresDate() {
        return measuresDate;
    }

    public void setMeasuresDate(Date measuresDate) {
        this.measuresDate = measuresDate;
    }

    public Status getResult() {
        return status;
    }

    public void setResult(Status status) {
        this.status = status;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
}
