package ru.ic.information_portal.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class StreetRoadNetwork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date createDate;
    private String username;

    @ManyToOne(targetEntity = Department.class, fetch = FetchType.EAGER)
    private Department department;

    private String foundWho;
    private String foundPlace;

    private Date foundDate;

    @ManyToOne(targetEntity = Shortcoming.class, fetch = FetchType.EAGER)
    private Shortcoming shortcoming;
    private String comment;

    @ManyToOne(targetEntity = Devices.class, fetch = FetchType.EAGER)
    private Devices devices;

    private String sendTo;
    private Date referralDate;

    @ManyToOne(targetEntity = Measures.class, fetch = FetchType.EAGER)
    private Measures measures;

    private String measureAgainst;
    private Date measuresDate;

    @ManyToOne(targetEntity = Status.class, fetch = FetchType.EAGER)
    private Status status;

    private Date closeDate;
    private String measuresIfNot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(Date foundDate) {
        this.foundDate = foundDate;
    }

    public Shortcoming getShortcoming() {
        return shortcoming;
    }

    public void setShortcoming(Shortcoming shortcoming) {
        this.shortcoming = shortcoming;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Devices getDevices() {
        return devices;
    }

    public void setDevices(Devices devices) {
        this.devices = devices;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public Date getReferralDate() {
        return referralDate;
    }

    public void setReferralDate(Date referralDate) {
        this.referralDate = referralDate;
    }

    public Measures getMeasures() {
        return measures;
    }

    public void setMeasures(Measures measures) {
        this.measures = measures;
    }

    public String getMeasureAgainst() {
        return measureAgainst;
    }

    public void setMeasureAgainst(String measureAgainst) {
        this.measureAgainst = measureAgainst;
    }

    public Date getMeasuresDate() {
        return measuresDate;
    }

    public void setMeasuresDate(Date measuresDate) {
        this.measuresDate = measuresDate;
    }


    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public String getMeasuresIfNot() {
        return measuresIfNot;
    }

    public void setMeasuresIfNot(String measuresIfNot) {
        this.measuresIfNot = measuresIfNot;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
