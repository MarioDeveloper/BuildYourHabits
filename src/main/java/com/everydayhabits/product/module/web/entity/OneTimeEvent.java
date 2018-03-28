package com.everydayhabits.product.module.web.entity;

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
    private Date plannedDate;

    @Column(name = "realization_date")
    private Date realizationDate;

    @Column(name = "experience_point")
    private int experience;

    @Column(name = "life_point")
    private int life;

    @Column(name = "is_done")
    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public OneTimeEvent() {
    }

    public OneTimeEvent(String title, String description, String difficultyLevel, Date plannedDate, int experience, int life, Boolean isDone) {
        this.title = title;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.plannedDate = plannedDate;
        this.experience = experience;
        this.life = life;
        this.isDone = isDone;
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

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public Date getRealizationDate() {
        return realizationDate;
    }

    public void setRealizationDate(Date realizationDate) {
        this.realizationDate = realizationDate;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "OneTimeEvent{" + "id=" + id + ", title='" + title + '\'' + ", description='" + description + '\'' + ", difficultyLevel='" + difficultyLevel + '\'' + ", planned_date=" + plannedDate + ", realization_date=" + realizationDate + ", experience_point=" + experience + ", life_point=" + life + ", user=" + user + '}';
    }
}
