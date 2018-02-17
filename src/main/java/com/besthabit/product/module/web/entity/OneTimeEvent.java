package com.besthabit.product.module.web.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "one_time_event")
public class OneTimeEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "difficulty_level")
    private String difficultyLevel;

    @Column(name = "planned_date")
    private Date planned_date;

    @Column(name = "realization_date")
    private Date realization_date;

    @Column(name = "experience_point")
    private Date experience_point;

    @Column(name = "life_point")
    private Date life_point; // default 10 if fail

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public OneTimeEvent() {
    }

    public OneTimeEvent(String description, String difficultyLevel, Date planned_date, Date realization_date, Date experience_point, Date life_point, User user) {
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.planned_date = planned_date;
        this.realization_date = realization_date;
        this.experience_point = experience_point;
        this.life_point = life_point;
        this.user = user;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Date getPlanned_date() {
        return planned_date;
    }

    public void setPlanned_date(Date planned_date) {
        this.planned_date = planned_date;
    }

    public Date getRealization_date() {
        return realization_date;
    }

    public void setRealization_date(Date realization_date) {
        this.realization_date = realization_date;
    }

    public Date getExperience_point() {
        return experience_point;
    }

    public void setExperience_point(Date experience_point) {
        this.experience_point = experience_point;
    }

    public Date getLife_point() {
        return life_point;
    }

    public void setLife_point(Date life_point) {
        this.life_point = life_point;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "OneTimeEvent{" + "id=" + id + ", title='" + title + '\'' + ", description='" + description + '\'' + ", difficultyLevel='" + difficultyLevel + '\'' + ", planned_date=" + planned_date + ", realization_date=" + realization_date + ", experience_point=" + experience_point + ", life_point=" + life_point + ", user=" + user + '}';
    }
}
