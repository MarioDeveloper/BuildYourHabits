package com.everydayhabits.product.module.web.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "challenge_event")
public class ChallengeEvent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_initiator_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notification_id")
    private Notification notification;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "difficulty_level")
    private String difficultyLevel;

    @Column(name = "isDone_user_initiator")
    private Boolean isDoneUserInit;

    @Column(name = "isDone_user_opponent")
    private Boolean isDoneUserOpponent;

    @Column(name = "isConfirmed")
    private Boolean isConfirmed;

    @Column(name = "planned_date")
    private Date plannedDate;

    @Column(name = "realization_date")
    private Date realizationDate;

    @Column(name = "experience_point")
    private int experience;

    @Column(name = "life_point")
    private int life;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "user_challenge",
            joinColumns=@JoinColumn(name = "challenge_event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public ChallengeEvent() {
    }

    public ChallengeEvent(User user, Notification notification, String title, String description, String difficultyLevel, Boolean isDoneUserInit, Boolean isDone_user_opponent, Boolean isConfirmed, Date plannedDate, Date realizationDate, int experience, int life) {
        this.user = user;
        this.notification = notification;
        this.title = title;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.isDoneUserInit = isDoneUserInit;
        this.isDoneUserOpponent = isDoneUserOpponent;
        this.isConfirmed = isConfirmed;
        this.plannedDate = plannedDate;
        this.realizationDate = realizationDate;
        this.experience = experience;
        this.life = life;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
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

    public Boolean getDoneUserInit() {
        return isDoneUserInit;
    }

    public void setDoneUserInit(Boolean doneUserInit) {
        isDoneUserInit = doneUserInit;
    }

    public Boolean getDoneUserOpponent() {
        return isDoneUserOpponent;
    }

    public void setDoneUserOpponent(Boolean doneUserOpponent) {
        isDoneUserOpponent = doneUserOpponent;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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



    public void addUser(User user){

        if(users == null) {
            users = new ArrayList<User>() {
            };
        }

        users.add(user);
    }

    @Override
    public String toString() {
        return "ChallengeEvent{" + "id=" + id + ", user=" + user + ", notification=" + notification + ", title='" + title + '\'' + ", description='" + description + '\'' + ", difficultyLevel='" + difficultyLevel + '\'' + ", isDoneUserInit=" + isDoneUserInit + ", isDone_user_opponent=" + isDoneUserOpponent + ", isConfirmed=" + isConfirmed + ", plannedDate=" + plannedDate + ", realizationDate=" + realizationDate + ", experience=" + experience + ", life=" + life + ", users=" + users + '}';
    }
}

