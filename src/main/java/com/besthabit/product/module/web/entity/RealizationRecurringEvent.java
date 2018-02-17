package com.besthabit.product.module.web.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "realization_recurring_event")
public class RealizationRecurringEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recurring_event_id")
    private RecurringEvent recurringEvent;

    @Column(name = "planned_date")
    private Date planned_date;

    @Column(name = "realization_date")
    private Date realization_date;

    @Column(name = "experience_point")
    private Date experience_point;

    @Column(name = "life_point")
    private Date life_point;


    public RealizationRecurringEvent() {
    }

    public RealizationRecurringEvent(RecurringEvent recurringEvent, Date planned_date, Date experience_point, Date life_point) {
        this.recurringEvent = recurringEvent;
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

    public Date getPlanned_date() {
        return planned_date;
    }

    public void setPlanned_date(Date planned_date) {
        this.planned_date = planned_date;
    }

    public Date getRealization_date() {
        return realization_date;
    }

    public void setRealization_date(Date realization_date) {
        this.realization_date = realization_date;
    }

    public Date getExperience_point() {
        return experience_point;
    }

    public void setExperience_point(Date experience_point) {
        this.experience_point = experience_point;
    }

    public Date getLife_point() {
        return life_point;
    }

    public void setLife_point(Date life_point) {
        this.life_point = life_point;
    }


    @Override
    public String toString() {
        return "RealizationRecurringEvent{" + "id=" + id + ", recurringEvent=" + recurringEvent + ", planned_date=" + planned_date + ", realization_date=" + realization_date + ", experience_point=" + experience_point + ", life_point=" + life_point + '}';
    }
}

