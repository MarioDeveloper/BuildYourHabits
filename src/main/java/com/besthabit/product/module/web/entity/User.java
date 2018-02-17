package com.besthabit.product.module.web.entity;

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

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_id")
    private Level level;

    @Column(name = "life")
    private int life;

    @Column(name = "experience")
    private int experience;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<RecurringEvent> recurringEvents;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<OneTimeEvent> oneTimeEvents;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "registration_date")
    private Date registration_date;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "user_reward",
            joinColumns=@JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "reward_id")
    )
    private List<Reward> rewards;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, int life, int experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public List<RecurringEvent> getRecurringEvents() {
        return recurringEvents;
    }

    public void setRecurringEvents(List<RecurringEvent> recurringEvents) {
        this.recurringEvents = recurringEvents;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }


    public void addReward(Reward theReward){

        if(rewards == null) {
            rewards = new ArrayList<Reward>();
        }

        rewards.add(theReward);
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

        @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", password='" + password + '\'' + ", level=" + level + ", life=" + life + ", experience=" + experience + ", image=" + Arrays.toString(image) + ", registration_date=" + registration_date + '}';
    }
}
