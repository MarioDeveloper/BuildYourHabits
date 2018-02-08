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

    @Column(name = "finish_date")
    private Date finishDate;

    public RealizationRecurringEvent() {
    }

    public RealizationRecurringEvent(Date finishDate) {
        this.finishDate = finishDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public RecurringEvent getRecurringEvent() {
        return recurringEvent;
    }

    public void setRecurringEvent(RecurringEvent recurringEvent) {
        this.recurringEvent = recurringEvent;
    }

    @Override
    public String toString() {
        return "RealizationRecurringEvent{" + "id=" + id + ", finishDate=" + finishDate + '}';
    }
}
