package com.everydayhabits.product.module.web.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name = "frequency_unit")
    private String frequencyUnit;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "difficulty_level")
    private String difficultyLevel;

    @Column(name = "start_date")
    private LocalDateTime start_date;

    @Column(name = "finish_date")
    private LocalDateTime finish_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "recurringEvent",  cascade = CascadeType.ALL)
    private List<RealizationRecurringEvent> realizationRecurringEvents;

    public RecurringEvent() {
    }

    public RecurringEvent(int frequency, String frequencyUnit, String title, String description, String difficultyLevel, LocalDateTime start_date, LocalDateTime finish_date) {
        this.frequency = frequency;
        this.frequencyUnit = frequencyUnit;
        this.title = title;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.start_date = start_date;
        this.finish_date = finish_date;
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

    public String getFrequencyUnit() {
        return frequencyUnit;
    }

    public void setFrequencyUnit(String frequencyUnit) {
        this.frequencyUnit = frequencyUnit;
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

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(LocalDateTime finish_date) {
        this.finish_date = finish_date;
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
            realizationRecurringEvents = new ArrayList<>();
        }

        realizationRecurringEvents.add(tempRealizationReccuringEvent);

        tempRealizationReccuringEvent.setRecurringEvent(this);

    }


    @Override
    public String toString() {
        return "RecurringEvent{" + "id=" + id + ", frequency='" + frequency + '\'' + ", title='" + title + '\'' + ", description='" + description + '\'' + ", difficultyLevel='" + difficultyLevel + '\'' + ", user=" + user + '}';
    }
}
