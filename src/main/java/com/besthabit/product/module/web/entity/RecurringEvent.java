package com.besthabit.product.module.web.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recurring_event")
public class RecurringEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "frequency")
    private int frequency;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "difficulty_level")
    private String difficultyLevel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "recurringEvent",  cascade = CascadeType.ALL)
    private List<RealizationRecurringEvent> realizationRecurringEvents;

    public RecurringEvent() {
    }

    public RecurringEvent(int frequency, String title, String description, String difficultyLevel, User user) {
        this.frequency = frequency;
        this.title = title;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getTitle() { return title; }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<RealizationRecurringEvent> getRealizationRecurringEvents() {
        return realizationRecurringEvents;
    }

    public void setRealizationRecurringEvents(List<RealizationRecurringEvent> realizationRecurringEvents) {
        this.realizationRecurringEvents = realizationRecurringEvents;
    }

    public void addRealizationRecurringEvent(RealizationRecurringEvent tempRealizationReccuringEvent) {

        if(realizationRecurringEvents == null) {
            realizationRecurringEvents = new ArrayList<RealizationRecurringEvent>();
        }

        realizationRecurringEvents.add(tempRealizationReccuringEvent);

        tempRealizationReccuringEvent.setRecurringEvent(this);

    }


    @Override
    public String toString() {
        return "RecurringEvent{" + "id=" + id + ", frequency='" + frequency + '\'' + ", title='" + title + '\'' + ", description='" + description + '\'' + ", difficultyLevel='" + difficultyLevel + '\'' + ", user=" + user + '}';
    }
}
