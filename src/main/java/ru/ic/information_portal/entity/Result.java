package ru.ic.information_portal.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Result {
    String title;
    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
