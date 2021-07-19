package ru.ic.information_portal.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Shortcoming {

    @Id
    private int id;
    private String title;
//    private int ;

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
