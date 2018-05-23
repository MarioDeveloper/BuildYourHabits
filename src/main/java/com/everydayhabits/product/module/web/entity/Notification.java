package com.everydayhabits.product.module.web.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "experience_point")
    private int experiencePoint;

    @Column(name = "date")
    private Date date;

    @Column(name = "type")
    private String type;

    @Column(name = "is_Display")
    private Boolean isDisplay;

    public Notification() {
    }

    public Notification(String firstName, String lastName, int experiencePoint, Date date, String type, Boolean isDisplay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experiencePoint = experiencePoint;
        this.date = date;
        this.type = type;
        this.isDisplay = isDisplay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getExperiencePoint() {
        return experiencePoint;
    }

    public void setExperiencePoint(int experiencePoint) {
        this.experiencePoint = experiencePoint;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDisplay() {
        return isDisplay;
    }

    public void setDisplay(Boolean display) {
        isDisplay = display;
    }

    @Override
    public String toString() {
        return "Notification{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", experiencePoint='" + experiencePoint + '\'' + ", date='"  +  date + '\'' + '}';
    }

}
