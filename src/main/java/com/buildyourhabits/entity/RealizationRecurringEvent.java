package com.buildyourhabits.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "realization_recurring_event")
public class RealizationRecurringEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "recurring_event_id")
    private RecurringEvent recurringEvent;

    @Column(name = "planned_date")
//    @Temporal(TemporalType.DATE)
    private Date plannedDate;

    @Column(name = "realization_date")
//    @Temporal(TemporalType.DATE)
    private Date realizationDate;

    @Column(name = "experience_point")
    private int experience;

    @Column(name = "life_point")
    private int life;

    @Column(name = "is_done")
    private Boolean isDone;


    public RealizationRecurringEvent() {
    }

    public RealizationRecurringEvent(Date plannedDate, int experience, int life, Boolean isDone) {
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

    public RecurringEvent getRecurringEvent() {
        return recurringEvent;
    }

    public void setRecurringEvent(RecurringEvent recurringEvent) {
        this.recurringEvent = recurringEvent;
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

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "RealizationRecurringEvent{" + "id=" + id + ", recurringEvent=" + recurringEvent + ", plannedDate=" + plannedDate + ", realizationDate=" + realizationDate + ", experience=" + experience + ", life=" + life + '}';
    }
}

