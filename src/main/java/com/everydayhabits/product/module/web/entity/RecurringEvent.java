package com.everydayhabits.product.module.web.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recurring_event")
public class RecurringEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "difficulty_level")
    private String difficultyLevel;

    @Column(name = "frequency")
    private int frequency;

    @Column(name = "frequency_unit")
    private String frequencyUnit;

    //    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;

    //    @Temporal(TemporalType.DATE)
    @Column(name = "finish_date")
    private Date finishDate;

    @Column(name = "is_done")
    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "recurringEvent",  cascade = CascadeType.ALL)
    private List<RealizationRecurringEvent> realizationRecurringEvents;

    public RecurringEvent() {
    }

    public RecurringEvent(String title, String description, String difficultyLevel, int frequency, String frequencyUnit, Date startDate, Date finishDate, Boolean isDone) {
        this.title = title;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.frequency = frequency;
        this.frequencyUnit = frequencyUnit;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.isDone = isDone;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
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

    public void setDone(Boolean done) {
        isDone = done;
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
