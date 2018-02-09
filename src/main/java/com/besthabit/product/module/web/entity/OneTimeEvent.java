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

    @Column(name = "finish_date")
    private Date finishDate;

    @Column(name = "isDone")
    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public OneTimeEvent() {
    }

    public OneTimeEvent(String title, String description, String difficultyLevel, Date finishDate, boolean isDone, User user) {
        this.title = title;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.finishDate = finishDate;
        this.isDone = isDone;
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

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "OneTimeEvent{" + "id=" + id + ", title='" + title + '\'' + ", description='" + description + '\'' + ", difficultyLevel='" + difficultyLevel + '\'' + ", finishDate=" + finishDate + ", isDone=" + isDone + ", user=" + user + '}';
    }
}
