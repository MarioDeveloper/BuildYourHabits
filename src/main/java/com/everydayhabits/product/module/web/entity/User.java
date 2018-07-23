package com.everydayhabits.product.module.web.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "city")
    private String city;

    @Column(name = "gender")
    private String gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_id")
    private Level level;

    @Column(name = "life")
    private int life;

    @Column(name = "experience")
    private int experience;

//    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<RecurringEvent> recurringEvents;

//    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OneTimeEvent> oneTimeEvents;

//    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications;

//    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ChallengeEvent> challengeEventInit;


    @Column(name = "image")
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @Column(name = "registration_date")
    private Date registrationDate;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinTable(
//            name = "user_reward",
//            joinColumns=@JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "reward_id")
//    )

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "user_challenge",
            joinColumns=@JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "challenge_event_id")
    )
    private List<ChallengeEvent> challengeEventList;

    public User() {
    }

    public User(String firstName, String lastName, String username, String password, String city, int life, int experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.city = city;
        this.life = life;
        this.experience = experience;
    }

/*    public User(String firstName, String lastName, String email, String password, Level level, int life, int experience, byte[] image, Date registration_date) {
            this.registration_date = registration_date;
            this.image = image;
            this.experience = experience;
            this.life = life;
            this.level = level;
            this.password = password;
            this.email = email;
            this.lastName = lastName;
            this.firstName = firstName;

    }*/

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }


    public List<RecurringEvent> getRecurringEvents() {
        return recurringEvents;
    }

    public List<OneTimeEvent> getOneTimeEvents() {
        return oneTimeEvents;
    }

    public void setOneTimeEvents(List<OneTimeEvent> oneTimeEvents) {
        this.oneTimeEvents = oneTimeEvents;
    }

    public void setRecurringEvents(List<RecurringEvent> recurringEvents) {
        this.recurringEvents = recurringEvents;
    }



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<ChallengeEvent> getChallengeEventInit() {
        return challengeEventInit;
    }

    public void setChallengeEventInit(List<ChallengeEvent> challengeEventInit) {
        this.challengeEventInit = challengeEventInit;
    }

    public List<ChallengeEvent> getChallengeEvents() {
        return challengeEventInit;
    }

    public void setChallengeEvents(List<ChallengeEvent> challengeEvents) {
        this.challengeEventInit = challengeEvents;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<ChallengeEvent> getChallengeEventList() {
        return challengeEventList;
    }

    public void setChallengeEventList(List<ChallengeEvent> challengeEventList) {
        this.challengeEventList = challengeEventList;
    }

    public void addRecuuringEvent(RecurringEvent tempRecurringEvent) {

        if(recurringEvents == null) {
            recurringEvents = new ArrayList<RecurringEvent>();
        }

        recurringEvents.add(tempRecurringEvent);

        tempRecurringEvent.setUser(this);
    }


    public void addOneTimeEvent(OneTimeEvent tempOneTimeEvent) {

        if(oneTimeEvents == null) {
            oneTimeEvents = new ArrayList<OneTimeEvent>();
        }

        oneTimeEvents.add(tempOneTimeEvent);

        tempOneTimeEvent.setUser(this);
    }

    public void addNotification(Notification tempNotification) {

        if(notifications == null) {
            notifications = new ArrayList<Notification>();
        }

        notifications.add(tempNotification);

        tempNotification.setUser(this);
    }

    public void addChallengeEventInit(ChallengeEvent tempChallengeEventInit) {

        if(challengeEventInit == null) {
            challengeEventInit = new ArrayList<ChallengeEvent>();
        }

        challengeEventInit.add(tempChallengeEventInit);

        tempChallengeEventInit.setUser(this);
    }

    public void addChallengeEvent(ChallengeEvent tempChallengeEvent) {

        if(challengeEventList == null) {
            challengeEventList = new ArrayList<ChallengeEvent>();
        }

        challengeEventList.add(tempChallengeEvent);

        tempChallengeEvent.setUser(this);
    }

        @Override
    public String toString() {
            return "User{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + username + '\'' + ", password='" + password + '\'' + ", level=" + level + ", life=" + life + ", experience=" + experience + ", image=" + Arrays.toString(image) + ", registration_date=" + registrationDate + '}';
    }

}
