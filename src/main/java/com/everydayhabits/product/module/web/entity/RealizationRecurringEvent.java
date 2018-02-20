package com.everydayhabits.product.module.web.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime planned_date;

    @Column(name = "realization_date")
    private LocalDateTime realization_date;

    @Column(name = "experience_point")
    private int experience_point;

    @Column(name = "life_point")
    private int life_point;


    public RealizationRecurringEvent() {
    }

    public RealizationRecurringEvent(LocalDateTime planned_date, int experience_point, int life_point) {
        this.planned_date = planned_date;
        this.experience_point = experience_point;
        this.life_point = life_point;
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

    public LocalDateTime getPlanned_date() {
        return planned_date;
    }

    public void setPlanned_date(LocalDateTime planned_date) {
        this.planned_date = planned_date;
    }

    public LocalDateTime getRealization_date() {
        return realization_date;
    }

    public void setRealization_date(LocalDateTime realization_date) {
        this.realization_date = realization_date;
    }

    public int getExperience_point() {
        return experience_point;
    }

    public void setExperience_point(int experience_point) {
        this.experience_point = experience_point;
    }

    public int getLife_point() {
        return life_point;
    }

    public void setLife_point(int life_point) {
        this.life_point = life_point;
    }

    @Override
    public String toString() {
        return "RealizationRecurringEvent{" + "id=" + id + ", recurringEvent=" + recurringEvent + ", planned_date=" + planned_date + ", realization_date=" + realization_date + ", experience_point=" + experience_point + ", life_point=" + life_point + '}';
    }
}

